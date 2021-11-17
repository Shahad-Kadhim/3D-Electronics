package com.lemon.team.electronics.ui.customerInformation

import androidx.navigation.fragment.findNavController
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCustomerInformationBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.Constants
import com.lemon.team.electronics.util.State
import com.lemon.team.electronics.util.observeEvent

class CustomerInformationFragment :
    BaseFragment<FragmentCustomerInformationBinding, CustomerInformationViewModel>() {

    override val layoutId = R.layout.fragment_customer_information
    override val viewModelClass = CustomerInformationViewModel::class.java

    override fun observeEvents() {
        viewModel.orderResponse.observeEvent(this) {
            if(it is State.Success){
                viewModel.onOrderSuccess()
                navigateToDialog(Constants.OrderStatus.SUCCESS)
            }
            if (it is State.Error){
                navigateToDialog(Constants.OrderStatus.ERROR)
            }
        }

    }

    override fun setUpBinding() {

    }

    private fun navigateToDialog(status: String){
        val action = CustomerInformationFragmentDirections.actionCustomerInformationFragmentToOrderStatusDialogFragment(status)
        findNavController().navigate(action)
    }

}