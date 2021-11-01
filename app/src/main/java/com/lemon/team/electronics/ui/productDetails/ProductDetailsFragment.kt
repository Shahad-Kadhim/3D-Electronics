package com.lemon.team.electronics.ui.productDetails

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentProductDetailsBinding
import com.lemon.team.electronics.ui.ImageActivity
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.Constants
import com.lemon.team.electronics.util.EventObserver

class ProductDetailsFragment :
    BaseFragment<FragmentProductDetailsBinding, ProductDetailsViewModel>() {

    override val layoutId: Int = R.layout.fragment_product_details
    override val viewModel: ProductDetailsViewModel by viewModels()
    private val args :ProductDetailsFragmentArgs by navArgs()
    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) ->
    FragmentProductDetailsBinding = DataBindingUtil::inflate

    override fun setUp() {
        binding.apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.viewModel = this@ProductDetailsFragment.viewModel
            productImages.adapter=ProductImageAdapter(emptyList(),this@ProductDetailsFragment.viewModel)
        }
        viewModel.getDetailsProduct(args.productId)
        observeEvents()
    }

    private fun observeEvents() {

        viewModel.onclickAddToCart.observe(this,EventObserver{
            // add to cart table when create database
        })

        viewModel.onclickWish.observe(this,EventObserver{
            // add to wish table when create database
        })

        viewModel.onclickShare.observe(this,EventObserver{
            // add code to share link of this product
        })

        viewModel.onclickBack.observe(this,EventObserver{
            findNavController().popBackStack()
        })

        viewModel.onclickMainImage.observe(this,EventObserver{
            goToImageActivity(it)
        })

    }

    private fun goToImageActivity(it: String) {
        startActivity(
            Intent(requireContext(),ImageActivity::class.java).apply {
                putExtra(Constants.MAIN_URL , it)
            }
        )
    }



}