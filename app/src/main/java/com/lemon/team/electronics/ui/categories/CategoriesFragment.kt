package com.lemon.team.electronics.ui.categories

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCategoriesBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.*


class CategoriesFragment: BaseFragment<FragmentCategoriesBinding,CategoriesViewModel>() {

    override val layoutId: Int = R.layout.fragment_categories
    override val viewModelClass = CategoriesViewModel::class.java


    override fun setUpBinding() {
        binding.apply {
            categoriesRecycler.adapter =
                CategoriesRecyclerAdapter(mutableListOf(),this@CategoriesFragment.viewModel)
        }
    }

    override fun observeEvents() {

        viewModel.categoryId.observeEvent(this){
            view?.goToFragment(CategoriesFragmentDirections
                .actionCategoriesFragmentToCategoryFragment(it.id, it.categoryName)
            )
        }

        viewModel.clickBackEvent.observeEvent(this){
            findNavController().popBackStack()
        }
    }

}