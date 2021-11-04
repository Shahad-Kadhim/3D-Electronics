package com.lemon.team.electronics.ui.productDetails

import android.content.Intent
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.*
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentProductDetailsBinding
import com.lemon.team.electronics.ui.ImageActivity
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.*

class ProductDetailsFragment :
    BaseFragment<FragmentProductDetailsBinding, ProductDetailsViewModel>() {

    override val layoutId: Int = R.layout.fragment_product_details
    override val viewModel: ProductDetailsViewModel by viewModels()
    private val args :ProductDetailsFragmentArgs by navArgs()
    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) ->
    FragmentProductDetailsBinding = DataBindingUtil::inflate

    override fun setUp() {
        super.setUp()
        viewModel.getDetailsProduct(args.productId)
    }


    override fun setUpBinding() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@ProductDetailsFragment.viewModel
            productImages.adapter=ProductImageRecyclerAdapter(emptyList(),this@ProductDetailsFragment.viewModel)
        }
    }

    override fun observeEvents() {

        viewModel.onclickAddToCart.observeEvent(this){
            // add to cart table when create database
        }

        viewModel.onclickWish.observeEvent(this){
            // add to wish table when create database
        }

        viewModel.onclickShare.observeEvent(this){
            // add code to share link of this product
        }

        viewModel.onclickBack.observeEvent(this){
            findNavController().popBackStack()
        }

        viewModel.onclickMainImage.observeEvent(this){
            goToImageActivity(it)
        }

    }

    private fun goToImageActivity(it: String) {
        startActivity(
            Intent(requireContext(),ImageActivity::class.java).apply {
                putExtra(Constants.MAIN_URL , it)
            }
        )
    }

}