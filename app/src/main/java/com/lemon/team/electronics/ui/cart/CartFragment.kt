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

    override fun setUpBinding(){
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@CartFragment.viewModel
            cartRecycler.adapter =
                CartRecyclerAdapter(mutableListOf(), this@CartFragment.viewModel)
        }
    }

    override fun observeEvents(){

        viewModel.clickItemEvent.observeEvent(this) {
            view?.goToFragment(CartFragmentDirections.actionCartFragmentToProductFragment(it))
        }

        viewModel.clickCheckoutEvent.observeEvent(this) {
            view?.goToFragment(CartFragmentDirections.actionCartFragmentToCheckoutFragment())
        }

        viewModel.clickBackEvent.observeEvent(this) {
            findNavController().popBackStack()
        }

    }


}