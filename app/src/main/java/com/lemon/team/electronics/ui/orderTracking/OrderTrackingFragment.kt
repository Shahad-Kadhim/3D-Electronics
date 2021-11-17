package com.lemon.team.electronics.ui.orderTracking

import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentOrderTrackingBinding
import com.lemon.team.electronics.ui.base.BaseFragment

class OrderTrackingFragment: BaseFragment<FragmentOrderTrackingBinding, OrderTrackingViewModel>() {

    override val layoutId = R.layout.fragment_order_tracking
    override val viewModelClass = OrderTrackingViewModel::class.java

    override fun observeEvents() {

    }

    override fun setUpBinding() {

    }
}