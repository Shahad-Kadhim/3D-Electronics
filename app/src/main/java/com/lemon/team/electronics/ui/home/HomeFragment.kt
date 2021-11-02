package com.lemon.team.electronics.ui.home

import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentHomeBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.EventObserver
import com.lemon.team.electronics.util.State

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutId: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentHomeBinding =
        DataBindingUtil::inflate

    override fun setUp() {
        binding.apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.viewModel = this@HomeFragment.viewModel
            initNestedAdapter()
            observeEvent()
        }
    }

    private fun initNestedAdapter() {
        binding.recyclerViewHome.adapter = HomeNestedAdapter(mutableListOf(HomeItem.SearchType()), viewModel)
    }

    private fun observeEvent() {
        viewModel.also {
            it.aboutEvent.observe(this, EventObserver {
//                binding.about.goToFragment(HomeFragmentDirections.actionHomeFragmentToAboutFragment())
            })
            it.cartEvent.observe(this, EventObserver {
//                binding.cart.goToFragment(HomeFragmentDirections.actionHomeFragmentToCartFragment())
            })

            (binding.recyclerViewHome.adapter as HomeNestedAdapter?).apply {

                it.mouseCategories.observe(this@HomeFragment) { state ->
                    if (state is State.Success) {
                        this?.addItem(HomeItem.ElementCategoriesType(state.toData()?.content!!
                            , "Mouse"))
                    }
                }

                it.categories.observe(this@HomeFragment) { state ->
                    if (state is State.Success) {
                        this?.addItem(HomeItem.CategoriesType(state.toData()!!, "Categories"))
                    }
                }

            }
        }
    }


}