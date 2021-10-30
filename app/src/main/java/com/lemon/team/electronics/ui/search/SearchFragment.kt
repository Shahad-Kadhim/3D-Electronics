package com.lemon.team.electronics.ui.search

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentSearchBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.EventObserver
import com.lemon.team.electronics.util.goToFragment

class SearchFragment:BaseFragment<FragmentSearchBinding,SearchViewModel>() {
    override val layoutId: Int = R.layout.fragment_search
    override val viewModel: SearchViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) ->
    FragmentSearchBinding = DataBindingUtil::inflate

    override fun setUp() {
        binding.apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.viewModel = this@SearchFragment.viewModel
            this.searchRecycler.adapter =
                SearchRecyclerAdapter(mutableListOf(),this@SearchFragment.viewModel)
        }
        observeEvents()
    }

    private fun observeEvents(){
        viewModel.clickItemEvent.observe(this,EventObserver{
            binding.root.goToFragment(
                SearchFragmentDirections
                    .actionSearchFragmentToProductFragment(it)
            )
        })

        viewModel.clickBackEvent.observe(this, EventObserver{
            findNavController().popBackStack()
        })
    }

}