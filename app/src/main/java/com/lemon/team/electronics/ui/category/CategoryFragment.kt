package com.lemon.team.electronics.ui.category

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCategoryBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.ui.categories.CategoriesAdapter

class CategoryFragment:BaseFragment<FragmentCategoryBinding, CategoryViewModel>() {
    override val layoutId: Int= R.layout.fragment_category
    override val viewModel: CategoryViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentCategoryBinding
        =DataBindingUtil::inflate

    override fun setUp() {
        binding.apply {
            this.lifecycleOwner= viewLifecycleOwner
            this.viewModel= this@CategoryFragment.viewModel
            this.categoryRecycler.adapter =
                CategoryAdapter(mutableListOf(), this@CategoryFragment.viewModel)
        }
    }

}