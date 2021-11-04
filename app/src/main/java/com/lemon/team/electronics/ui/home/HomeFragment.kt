package com.lemon.team.electronics.ui.home

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentHomeBinding
import com.lemon.team.electronics.model.domain.HomeItem
import com.lemon.team.electronics.model.domain.CategoryInfoType
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.Constants.MOUSE_CATEGORY_ID
import com.lemon.team.electronics.util.Constants.MOUSE_TITLE
import com.lemon.team.electronics.util.State
import com.lemon.team.electronics.util.goToFragment
import com.lemon.team.electronics.util.observeEvent

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutId: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentHomeBinding =
        DataBindingUtil::inflate

    override fun setUpBinding() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@HomeFragment.viewModel
            recyclerViewHome.adapter =
                HomeRecyclerAdapter(mutableListOf(HomeItem.SearchType()), this@HomeFragment.viewModel)
        }

    }

    override fun observeEvents() {
        viewModel.also {
            it.aboutEvent.observeEvent(this) {
                binding.about.goToFragment(HomeFragmentDirections.actionHomeFragmentToAboutFragment())
            }
            it.cartEvent.observeEvent(this) {
                binding.cart.goToFragment(HomeFragmentDirections.actionHomeFragmentToCartFragment())
            }
            it.searchEvent.observeEvent(this) {
                binding.root.goToFragment(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
            }
            it.onclickCategoryEvent.observeEvent(this) { category ->
                binding.root.goToFragment(HomeFragmentDirections.actionHomeFragmentToCategoryFragment(
                    category.id,
                    category.categoryName))
            }
            it.onclickProductEvent.observeEvent(this) { id ->
                binding.root.goToFragment(HomeFragmentDirections.actionHomeFragmentToProductFragment(
                    id))
            }

            it.clickSeeMoreForCategories.observeEvent(this) {
                binding.root.goToFragment(HomeFragmentDirections.actionHomeFragmentToCategoriesFragment())
            }
            it.clickSeeMoreForBestSeller.observeEvent(this) {
                //  binding.root.goToFragment(HomeFragmentDirections)
            }
            it.clickSeeMoreForCategory.observeEvent(this) {
                binding.root.goToFragment(HomeFragmentDirections
                    .actionHomeFragmentToCategoryFragment(it.categoryId, it.categoryName))
            }

        }
    }

    override fun setUp() {
        super.setUp()
        observeResponse()
    }

    fun observeResponse() {
        viewModel.also {
            (binding.recyclerViewHome.adapter as HomeRecyclerAdapter?).apply {

                it.mouseCategories.observe(this@HomeFragment) { state ->
                    if (state is State.Success) {
                        this?.addItem(
                            HomeItem.ElementCategoriesType(state.toData()?.products!!,
                                CategoryInfoType(MOUSE_CATEGORY_ID, MOUSE_TITLE)))
                    }
                }

                it.categories.observe(this@HomeFragment) { state ->
                    if (state is State.Success) {
                        this?.addItem(HomeItem.CategoriesType(state.toData()!!))
                    }
                }

                it.bestProduct.observe(this@HomeFragment) { state ->
                    if (state is State.Success) {
                        this?.addItem(HomeItem.BestProductType(state.toData()!!))
                        this?.addItem(HomeItem.SlideType(state.toData()!!))
                    }
                }

            }
        }
    }

}
