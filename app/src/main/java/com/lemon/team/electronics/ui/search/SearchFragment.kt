package com.lemon.team.electronics.ui.search

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentSearchBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.*

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override val layoutId: Int = R.layout.fragment_search
    override val viewModel: SearchViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) ->
    FragmentSearchBinding = DataBindingUtil::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater
                .from(context)
                .inflateTransition(android.R.transition.move)
    }

    override fun setUpBinding() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@SearchFragment.viewModel
            searchRecycler.adapter =
                SearchRecyclerAdapter(mutableListOf(), this@SearchFragment.viewModel)
        }
    }

    override fun observeEvents() {
        viewModel.clickItemEvent.observeEvent(this){
            binding.root.goToFragment(
                SearchFragmentDirections
                    .actionSearchFragmentToProductFragment(it)
            )
        }

        viewModel.clickBackEvent.observeEvent(this) {
            findNavController().popBackStack()
        }
    }

}