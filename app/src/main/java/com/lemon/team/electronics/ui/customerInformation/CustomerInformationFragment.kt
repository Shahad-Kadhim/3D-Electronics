package com.lemon.team.electronics.ui.customerInformation

import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCustomerInformationBinding
import com.lemon.team.electronics.model.orderResponse.OrderResponse
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.ui.customerInformation.orderStatus.OrderStatus
import com.lemon.team.electronics.util.Constants
import com.lemon.team.electronics.util.State
import com.lemon.team.electronics.util.observeEvent

class CustomerInformationFragment :
    BaseFragment<FragmentCustomerInformationBinding, CustomerInformationViewModel>() {

    override val layoutId = R.layout.fragment_customer_information
    override val viewModelClass = CustomerInformationViewModel::class.java

    override fun observeEvents() {
        viewModel.orderResponse.observeEvent(this) {
            if (checkCustomerInformationValidationAndSetErrors()) {
                openDialog(it)
            } else {
                Toast.makeText(this.context, getString(R.string.invalid_information), Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.clickBackEvent.observeEvent(this){
            findNavController().navigateUp()
        }

    }

    private fun openDialog(state: State<OrderResponse?>) {
        when (state) {
            is State.Success -> navigateToDialog(OrderStatus.Success)
            State.Loading -> {}
            is State.Error -> navigateToDialog(OrderStatus.Fail)
        }
    }

    private fun checkCustomerInformationValidationAndSetErrors(): Boolean {
        var isValid = true
        binding.apply {
            if (fullNameInput.text.isEmpty()) {
                fullNameInput.error = getString(R.string.enter_your_full_name)
                isValid = false
            }
            if (regionNameInput.text.isEmpty()) {
                regionNameInput.error = getString(R.string.enter_your_region_name)
                isValid = false
            }
            if (phoneNumberInput.text.isEmpty()) {
                phoneNumberInput.error = getString(R.string.enter_your_phone_number)
                isValid = false
            } else if (phoneNumberInput.text.count() != Constants.VALID_NUMBER_OF_DIGIT_OF_PHONE_NUMBER) {
                phoneNumberInput.error = getString(R.string.phone_number_should_be_11_digit)
                isValid = false
            }

        }
        return isValid
    }

    override fun setUpBinding() {

    }

    private fun navigateToDialog(status: OrderStatus) {
        val action =
            CustomerInformationFragmentDirections.actionCustomerInformationFragmentToOrderStatusDialogFragment(
                status
            )
        findNavController().navigate(action)
    }

}