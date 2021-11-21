package com.lemon.team.electronics.ui.customerInformation

import androidx.navigation.fragment.findNavController
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCustomerInformationBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.ui.customerInformation.orderStatus.OrderStatus
import com.lemon.team.electronics.util.State
import com.lemon.team.electronics.util.observeEvent

class CustomerInformationFragment :
    BaseFragment<FragmentCustomerInformationBinding, CustomerInformationViewModel>() {

    override val layoutId = R.layout.fragment_customer_information
    override val viewModelClass = CustomerInformationViewModel::class.java
    override val useActivityViewModel = false

    override fun observeEvents() {
        with(viewModel) {
            orderResponse.observeEvent(this@CustomerInformationFragment) {
                when (it) {
                    is State.Success -> {
                        viewModel.clearCartItems()
                        navigateToDialog(OrderStatus.Success)
                    }
                    State.Loading -> {
                    }
                    is State.Error -> navigateToDialog(OrderStatus.Fail)
                }
            }

            clickBackEvent.observeEvent(this@CustomerInformationFragment) {
                findNavController().navigateUp()
            }

        }
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