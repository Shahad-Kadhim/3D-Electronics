package com.lemon.team.electronics.ui.productDetails

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentProductDetailsBinding
import com.lemon.team.electronics.ui.base.BaseFragment

class ProductDetailsFragment:BaseFragment<FragmentProductDetailsBinding,ProductDetailsViewModel>() {
    override val layoutId: Int = R.layout.fragment_product_details
    override val viewModel: ProductDetailsViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentProductDetailsBinding
        =DataBindingUtil::inflate

    override fun setUp() {

    }

}