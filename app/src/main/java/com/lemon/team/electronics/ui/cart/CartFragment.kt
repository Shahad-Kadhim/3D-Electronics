package com.lemon.team.electronics.ui.cart

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCartBinding
import com.lemon.team.electronics.ui.base.BaseFragment

class CartFragment:BaseFragment<FragmentCartBinding,CartViewModel>() {
    override val layoutId: Int = R.layout.fragment_cart
    override val viewModel: CartViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentCartBinding
        =DataBindingUtil::inflate

    override fun setUp() {

    }

}