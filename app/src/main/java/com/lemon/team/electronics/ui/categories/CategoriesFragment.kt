package com.lemon.team.electronics.ui.categories

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCategoriesBinding
import com.lemon.team.electronics.model.response.CategoryResponse
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
        with(viewModel) {
            categoryId.observeEvent(this@CategoriesFragment) { categoryResponse ->
                view?.goToFragment(CategoriesFragmentDirections
                    .actionCategoriesFragmentToCategoryFragment(categoryResponse.id,
                        categoryResponse.categoryName)
                )
            }
            clickBackEvent.observeEvent(this@CategoriesFragment) {
                findNavController().navigateUp()
            }
        }
    }

}