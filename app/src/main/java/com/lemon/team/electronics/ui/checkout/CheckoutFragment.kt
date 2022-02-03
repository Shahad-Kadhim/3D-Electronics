package com.lemon.team.electronics.ui.checkout

import android.view.*
import androidx.navigation.fragment.findNavController
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCheckoutBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.ui.cart.CartViewModel
import com.lemon.team.electronics.util.goToFragment
import com.lemon.team.electronics.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutFragment : BaseFragment<FragmentCheckoutBinding, CartViewModel>() {

    override val useActivityViewModel = false

    override val layoutId: Int = R.layout.fragment_checkout
    override val viewModelClass = CartViewModel::class.java

    override fun observeEvents() {
        viewModel.also {
            it.clickPayNowEvent.observeEvent(this) {
                binding.root.goToFragment(CheckoutFragmentDirections.actionCheckoutFragmentToCustomerInformationFragment())
            }
            it.clickBackEvent.observeEvent(this) {
                findNavController().navigateUp()
            }
        }
    }

    override fun setUpBinding() {
        binding.apply {
            checkoutRecycler.adapter =
                PurchasedProductsRecyclerAdapter(mutableListOf(), this@CheckoutFragment.viewModel)
        }
    }

}