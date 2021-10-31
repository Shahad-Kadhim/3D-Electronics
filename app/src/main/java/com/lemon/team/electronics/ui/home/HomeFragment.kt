package com.lemon.team.electronics.ui.home

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentHomeBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.*

class HomeFragment:BaseFragment<FragmentHomeBinding,HomeViewModel>() , HomeInteractionListener {
    override val layoutId: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentHomeBinding
        =DataBindingUtil::inflate

    override fun setUp() {

        binding.apply {
            this.lifecycleOwner= viewLifecycleOwner
            this.viewModel= this@HomeFragment.viewModel
            observeEvent()
        }

    }

    private fun observeEvent() {

        viewModel.also {
            it.aboutEvent.observe(this, EventObserver{
                binding.about.goToFragment(HomeFragmentDirections.actionHomeFragmentToAboutFragment())
            })
            it.cartEvent.observe(this, EventObserver{
                binding.cart.goToFragment(HomeFragmentDirections.actionHomeFragmentToCartFragment())
            })
        }

    }



}