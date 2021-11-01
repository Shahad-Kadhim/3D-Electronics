package com.lemon.team.electronics.ui.cart

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCartBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.*

class CartFragment: BaseFragment<FragmentCartBinding, CartViewModel>() {

    override val layoutId: Int = R.layout.fragment_cart
    override val viewModel: CartViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentCartBinding
        = DataBindingUtil::inflate

    override fun setUp() {
        binding.apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.viewModel = this@CartFragment.viewModel
            this.cartRecycler.adapter =
                CartRecyclerAdapter(mutableListOf(), this@CartFragment.viewModel)
        }
        observeEvents()
    }

    private fun observeEvents(){

        viewModel.clickItemEvent.observe(this, EventObserver{
            view?.goToFragment(CartFragmentDirections.actionCartFragmentToProductFragment(it))
        })

        viewModel.clickCheckoutEvent.observe(this, EventObserver{
            view?.goToFragment(CartFragmentDirections.actionCartFragmentToCheckoutFragment())
        })

        viewModel.clickBackEvent.observe(this, EventObserver{
            findNavController().popBackStack()
        })

    }



}