package com.lemon.team.electronics.ui.home

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.airbnb.lottie.animation.content.Content
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentHomeBinding
import com.lemon.team.electronics.model.response.categories.CategoriesResponseItem
import com.lemon.team.electronics.model.response.productById.ProductResponse
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.EventObserver

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

        val itemsList: MutableList<HomeItems<Any>> = mutableListOf()

        itemsList.add(HomeItems(null, HomeItemsType.TYPE_SLIDE_SHOW))
        itemsList.add(HomeItems(null, HomeItemsType.TYPE_SEARCH))
        itemsList.add(HomeItems("best seller", HomeItemsType.TYPE_BEST_SELLER))
        itemsList.add(HomeItems("categories", HomeItemsType.TYPE_CATEGORIES))
        itemsList.add(HomeItems(  "categories_element", HomeItemsType.TYPE_ELEMENTS_CATEGORIES))

        val adapter = HomeNestedAdapter(itemsList, this)
        binding.recyclerViewHome.adapter = adapter

    }

    private fun observeEvent() {

        viewModel.also {
            it.aboutEvent.observe(this, EventObserver{
//                binding.about.goToFragment(HomeFragmentDirections.actionHomeFragmentToAboutFragment())
            })
            it.cartEvent.observe(this, EventObserver{
//                binding.cart.goToFragment(HomeFragmentDirections.actionHomeFragmentToCartFragment())
            })
        }

    }



}