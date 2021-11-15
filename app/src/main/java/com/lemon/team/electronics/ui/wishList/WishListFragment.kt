package com.lemon.team.electronics.ui.wishList

import android.util.Log
import android.view.*
import androidx.navigation.fragment.*
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentWishListBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.ui.productDetails.ProductDetailsFragmentDirections
import com.lemon.team.electronics.util.*

class WishListFragment: BaseFragment<FragmentWishListBinding, WishListViewModel>() {

    override val layoutId: Int = R.layout.fragment_wish_list
    override val viewModelClass = WishListViewModel::class.java

    override fun setUpBinding() {
        binding.apply {
            wishlistRecycler.adapter =
                WishListRecyclerAdapter(mutableListOf(),this@WishListFragment.viewModel)
        }
    }

    override fun observeEvents(){

        viewModel.clickItemEvent.observeEvent(this){ productId ->
            view?.goToFragment(
                WishListFragmentDirections.actionWishFragment2ToProductFragment(productId)
            )
        }
        viewModel.clickBackEvent.observeEvent(this){
            findNavController().navigateUp()
        }

        viewModel.clickAdd.observeEvent(this) {
            view?.goToFragment(
                WishListFragmentDirections.actionWishListFragmentToCartFragment()
            )
        }

    }


}