package com.lemon.team.electronics.ui.orderTracking

import androidx.navigation.fragment.findNavController
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentOrderTrackingBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.observeEvent

class OrderTrackingFragment: BaseFragment<FragmentOrderTrackingBinding, OrderTrackingViewModel>() {

    override val layoutId = R.layout.fragment_order_tracking
    override val viewModelClass = OrderTrackingViewModel::class.java
    override val useActivityViewModel: Boolean = false

    override fun setUpBinding() {
        binding.apply {
            orderRecycler.adapter =
                OrderTrackingRecyclerAdapter(mutableListOf(), this@OrderTrackingFragment.viewModel)
        }
    }

    override fun observeEvents() {
        viewModel.clickBackEvent.observeEvent(this) {
            findNavController().navigateUp()
        }
    }

}