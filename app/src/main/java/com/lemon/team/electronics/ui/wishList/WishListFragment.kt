package com.lemon.team.electronics.ui.wishList

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentWishListBinding
import com.lemon.team.electronics.ui.base.BaseFragment

class WishListFragment: BaseFragment<FragmentWishListBinding, WishListViewModel>() {
    override val layoutId: Int = R.layout.fragment_wish_list
    override val viewModel: WishListViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentWishListBinding
        = DataBindingUtil::inflate

    override fun setUp() {
        binding.apply {
            this.lifecycleOwner= viewLifecycleOwner
            this.viewModel= this@WishListFragment.viewModel
            this.wishlistRecycler.adapter =
                WishListRecyclerAdapter(mutableListOf(),this@WishListFragment.viewModel)
        }
    }

}