package com.lemon.team.electronics.ui.home

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentHomeBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.EventObserver
import com.lemon.team.electronics.util.State
import com.lemon.team.electronics.util.goToFragment

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
                binding.about.goToFragment(HomeFragmentDirections.actionHomeFragmentToAboutFragment())
            })
            it.cartEvent.observe(this, EventObserver {
                binding.cart.goToFragment(HomeFragmentDirections.actionHomeFragmentToCartFragment())
            })
            it.searchEvent.observe(this, EventObserver {
                binding.root.goToFragment(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
            })
            it.onclickCategoryEvent.observe(this, EventObserver { category ->
                binding.root.goToFragment(HomeFragmentDirections.actionHomeFragmentToCategoryFragment(category.id,category.categoryName))
            })
            it.onclickProductEvent.observe(this, EventObserver { id ->
                binding.root.goToFragment(HomeFragmentDirections.actionHomeFragmentToProductFragment(id))
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
                        this?.addItem(HomeItem.CategoriesType(state.toData()!!))
                    }
                }

                it.bestProduct.observe(this@HomeFragment) { state ->
                    if (state is State.Success) {
                        this?.addItem(HomeItem.BestProductType(state.toData()!!,"Best Seller"))
                        this?.addItem(HomeItem.SlideType(state.toData()!!))
                    }
                }

            }
        }
    }


}