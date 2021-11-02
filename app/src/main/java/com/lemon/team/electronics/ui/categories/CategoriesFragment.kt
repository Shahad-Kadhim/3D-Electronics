package com.lemon.team.electronics.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCategoriesBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.EventObserver
import com.lemon.team.electronics.util.goToFragment


class CategoriesFragment:BaseFragment<FragmentCategoriesBinding,CategoriesViewModel>() {

    override val layoutId: Int = R.layout.fragment_categories
    override val viewModel: CategoriesViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentCategoriesBinding
        =DataBindingUtil::inflate

    override fun setUp() {
        binding.apply {
            lifecycleOwner= viewLifecycleOwner
            viewModel= this@CategoriesFragment.viewModel
            categoriesRecycler.adapter =
                CategoriesAdapter(mutableListOf(),this@CategoriesFragment.viewModel)
        }

        observeEvent()
    }

    private fun observeEvent() {
        viewModel.categoryId.observe(this, EventObserver{
            view?.goToFragment(CategoriesFragmentDirections
                .actionCategoriesFragmentToCategoryFragment(it.id, it.categoryName)
            )
        })
        viewModel.clickBackEvent.observe(this, EventObserver{
            findNavController().popBackStack()
        })
    }

}