package com.lemon.team.electronics.ui.cart

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCartBinding
import com.lemon.team.electronics.ui.about.AboutViewModel
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.*

class CartFragment: BaseFragment<FragmentCartBinding, CartViewModel>() {

    override val layoutId: Int = R.layout.fragment_cart
    override val viewModelClass = CartViewModel::class.java


    override fun setUpBinding(){
        binding.apply {
            cartRecycler.adapter =
                CartRecyclerAdapter(mutableListOf(), this@CartFragment.viewModel)
        }
    }

    override fun observeEvents(){

        viewModel.clickItemEvent.observeEvent(this) { productId ->
            view?.goToFragment(CartFragmentDirections.actionCartFragmentToProductFragment(productId))
        }

        viewModel.clickCheckoutEvent.observeEvent(this) {
            view?.goToFragment(CartFragmentDirections.actionCartFragmentToCheckoutFragment())
        }

        viewModel.clickBackEvent.observeEvent(this) {
            findNavController().popBackStack()
        }

    }


}