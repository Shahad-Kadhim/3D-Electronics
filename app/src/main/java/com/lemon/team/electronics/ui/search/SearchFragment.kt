package com.lemon.team.electronics.ui.search

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.*
import androidx.navigation.fragment.findNavController
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentSearchBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.*

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    override val layoutId: Int = R.layout.fragment_search
    override val viewModelClass = SearchViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater
                .from(context)
                .inflateTransition(android.R.transition.move)
    }

    override fun setUpBinding() {
        binding.apply {
            searchRecycler.adapter =
                SearchRecyclerAdapter(mutableListOf(), this@SearchFragment.viewModel)
        }
    }

    override fun observeEvents() {
        viewModel.clickItemEvent.observeEvent(this){ productId ->
            binding.root.goToFragment(
                SearchFragmentDirections
                    .actionSearchFragmentToProductFragment(productId)
            )
        }

        viewModel.clickBackEvent.observeEvent(this) {
            findNavController().navigateUp()
        }
    }

}