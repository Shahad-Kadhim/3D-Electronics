package com.lemon.team.electronics.ui.home

import android.view.*
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentHomeBinding
import com.lemon.team.electronics.model.domain.*
import com.lemon.team.electronics.ui.base.BaseFragment
import android.content.Intent
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import com.lemon.team.electronics.model.response.ProductsResponse
import com.lemon.team.electronics.util.*


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutId: Int = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class.java

    override fun setUpBinding() {
        binding.apply {
            recyclerViewHome.adapter =
                HomeRecyclerAdapter(mutableListOf(HomeItem.SearchType()), this@HomeFragment.viewModel)
        }

    }

    override fun setUp() {
        super.setUp()
        observeResponse()
    }

    override fun observeEvents() {
        viewModel.apply {
            navOnEvent(aboutEvent){
                HomeFragmentDirections.actionHomeFragmentToAboutFragment()
            }
            navOnEvent(cartEvent){
                HomeFragmentDirections.actionHomeFragmentToCartFragment()
            }
            navOnEvent(onclickCategoryEvent){ category ->
                HomeFragmentDirections.actionHomeFragmentToCategoryFragment(
                    category.id,
                    category.categoryName
                )
            }
            navOnEvent(onclickProductEvent){ id ->
                HomeFragmentDirections.actionHomeFragmentToProductFragment(id)
            }

            navOnEvent(clickSeeMoreForCategories){
                HomeFragmentDirections.actionHomeFragmentToCategoriesFragment()
            }
            navOnEvent(clickSeeMoreForCategory){ categoryInfo ->
                HomeFragmentDirections
                    .actionHomeFragmentToCategoryFragment(
                        categoryInfo.categoryId,
                        categoryInfo.categoryName
                    )
            }

            searchEvent.observeEvent(this@HomeFragment) {
                binding.root.goToFragmentWithTransition(
                    HomeFragmentDirections.actionHomeFragmentToSearchFragment(),
                    FragmentNavigatorExtras(requireActivity().findViewById<CardView>(R.id.searchCard) to "search")
                    )
            }

            clickSharedProduct.observeEvent(this@HomeFragment) { productUrl ->
                shareProduct(productUrl)
            }

        }
    }

    private fun <T>navOnEvent(event: LiveData<Event<T>>, action:(T) -> NavDirections ){
        event.observeEvent(this) {
            binding.root.goToFragment(action(it))
        }
    }

    private fun shareProduct(productUrl:String){
        startActivity(
            Intent.createChooser(
                Intent(Intent.ACTION_SEND).sharingUrl(productUrl), "Share using")
        )
    }

    private fun observeResponse() {
        viewModel.apply {
            (binding.recyclerViewHome.adapter as HomeRecyclerAdapter?)?.apply {

                addCategoryItem(mouseCategory,CategoryInfoType(Constants.MOUSE_CATEGORY_ID, Constants.MOUSE_TITLE))
                addCategoryItem(headphoneCategory,CategoryInfoType(Constants.HEADPHONE_CATEGORY_ID, Constants.HEADPHONE_TITLE))

                addItem(categories){ state ->
                    addItem(HomeItem.CategoriesType(state.toData()!!.shuffled()))
                }

                addItem(bestProduct){ state ->
                    addItem(HomeItem.BestProductType(state.toData()!!))
                }

                addItem(homeImages){ state ->
                    addItem(HomeItem.SlideType(state.toData()!!))
                }

            }
        }
    }

    private fun addCategoryItem(
        category: LiveData<State<ProductsResponse?>>,
        categoryInfoType: CategoryInfoType
    ){
        addItem(category){
            (binding.recyclerViewHome.adapter as HomeRecyclerAdapter?)?.apply {
                this.addItem(HomeItem.ElementCategoriesType(it.toData()?.products!!, categoryInfoType))
            }
        }
    }

    private fun <T>addItem(
        response :  LiveData<State<T?>>,
        add: (State.Success<T?>) ->Unit
    ){
        (binding.recyclerViewHome.adapter as HomeRecyclerAdapter?)?.apply {
            response.observe(this@HomeFragment) { state ->
                if (state is State.Success) {
                    add(state)
                }
            }
        }
    }

}
