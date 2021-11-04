package com.lemon.team.electronics.ui.checkout

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCheckoutBinding
import com.lemon.team.electronics.ui.base.BaseFragment

class CheckoutFragment:BaseFragment<FragmentCheckoutBinding,CheckoutViewModel>() {
    override val layoutId: Int = R.layout.fragment_checkout
    override val viewModel: CheckoutViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentCheckoutBinding
        =DataBindingUtil::inflate

    override fun observeEvents() {

    }

    override fun setUpBinding() {

    }

}