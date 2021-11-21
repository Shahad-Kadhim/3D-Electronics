package com.lemon.team.electronics.ui.wishList

import android.view.*
import androidx.navigation.fragment.*
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentWishListBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.*

class WishListFragment: BaseFragment<FragmentWishListBinding, WishListViewModel>() {

    override val layoutId: Int = R.layout.fragment_wish_list
    override val viewModelClass = WishListViewModel::class.java

    override fun setUpBinding() {
        binding.apply {
            wishlistRecycler.adapter =
                WishListRecyclerAdapter(mutableListOf(), this@WishListFragment.viewModel)
        }
    }

    override fun observeEvents() {
        with(viewModel) {
            clickItemEvent.observeEvent(this@WishListFragment) { productId ->
                view?.goToFragment(
                    WishListFragmentDirections.actionWishFragment2ToProductFragment(productId)
                )
            }
            clickBackEvent.observeEvent(this@WishListFragment) {
                findNavController().navigateUp()
            }

            clickAdd.observeEvent(this@WishListFragment) {
                view?.goToFragment(
                    WishListFragmentDirections.actionWishListFragmentToCartFragment()
                )
            }
            toast.observeEvent(this@WishListFragment) {
                setToast(view, it)
            }
        }
    }
}