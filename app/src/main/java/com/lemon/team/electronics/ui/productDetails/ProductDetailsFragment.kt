package com.lemon.team.electronics.ui.productDetails

import android.content.Intent
import android.util.Log
import android.view.*
import androidx.navigation.fragment.*
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentProductDetailsBinding
import com.lemon.team.electronics.ui.image.ImageActivity
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.*

class ProductDetailsFragment :
    BaseFragment<FragmentProductDetailsBinding, ProductDetailsViewModel>() {

    override val useActivityViewModel = false

    override val layoutId: Int = R.layout.fragment_product_details
    private val args: ProductDetailsFragmentArgs by navArgs()
    override val viewModelClass = ProductDetailsViewModel::class.java

    override fun setUp() {
        super.setUp()
        viewModel.getDetailsProduct(args.productId)
    }


    override fun setUpBinding() {
        binding.apply {
            productImagesRecycler.adapter =
                ProductImageRecyclerAdapter(emptyList(), this@ProductDetailsFragment.viewModel)
        }
    }

    override fun observeEvents() {
        viewModel.apply {

            onclickWish.observeEvent(this@ProductDetailsFragment) {
                // add to wish table when create database
            }

            onclickBack.observeEvent(this@ProductDetailsFragment) {
                findNavController().navigateUp()
            }

            onclickMainImage.observeEvent(this@ProductDetailsFragment) { imageUrl ->
                goToImageActivity(imageUrl)
            }

            clickSharedProduct.observeEvent(this@ProductDetailsFragment) { imageUrl ->
                startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).sharingUrl(imageUrl)
                    , Constants.SHARE_KEY))
            }
        }
    }

    private fun goToImageActivity(imageUrl: String) {
        startActivity(
            Intent(requireContext(), ImageActivity::class.java).apply {
                putExtra(Constants.MAIN_URL, imageUrl)
            }
        )
    }

}