package com.lemon.team.electronics.ui.cart

import android.view.*
import androidx.navigation.fragment.findNavController
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCartBinding
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
        with(viewModel) {
            clickItemEvent.observeEvent(this@CartFragment) { productId ->
                view?.goToFragment(CartFragmentDirections.actionCartFragmentToProductFragment(
                    productId))
            }

            clickCheckoutEvent.observeEvent(this@CartFragment) {
                view?.goToFragment(CartFragmentDirections.actionCartFragmentToCheckoutFragment())
            }

            clickBackEvent.observeEvent(this@CartFragment) {
                findNavController().navigateUp()
            }
        }
    }


}