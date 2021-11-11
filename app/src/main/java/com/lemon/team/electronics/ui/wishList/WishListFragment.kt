package com.lemon.team.electronics.ui.wishList

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.*
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentWishListBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.*

class WishListFragment: BaseFragment<FragmentWishListBinding, WishListViewModel>(WishListViewModel::class.java) {

    override val layoutId: Int = R.layout.fragment_wish_list

    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentWishListBinding
        = DataBindingUtil::inflate

    override fun setUpBinding() {
        binding.apply {
            wishlistRecycler.adapter =
                WishListRecyclerAdapter(mutableListOf(),this@WishListFragment.viewModel)
        }
    }

    override fun observeEvents(){
        viewModel.clickItemEvent.observeEvent(this){
            view?.goToFragment(
                WishListFragmentDirections.actionWishFragment2ToProductFragment(it)
            )
        }
        viewModel.clickBackEvent.observeEvent(this){
            findNavController().popBackStack()
        }

    }

}