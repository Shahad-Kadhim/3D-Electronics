package com.lemon.team.electronics.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentProductBinding
import com.lemon.team.electronics.ui.base.BaseFragment

class ProductFragment:BaseFragment<FragmentProductBinding,ProductViewModel>() {
    override val layoutId: Int = R.layout.fragment_product
    override val viewModel: ProductViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentProductBinding
        =DataBindingUtil::inflate

    override fun setUp() {

    }

}