package com.lemon.team.electronics.ui.category

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCategoryBinding
import com.lemon.team.electronics.ui.base.BaseFragment

class CategoryFragment:BaseFragment<FragmentCategoryBinding, CategoryViewModel>() {

    override val layoutId: Int= R.layout.fragment_category
    override val viewModel: CategoryViewModel by viewModels()
    val args: CategoryFragmentArgs by navArgs()

    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentCategoryBinding
        =DataBindingUtil::inflate

    override fun setUp() {
        binding.apply {
            this.lifecycleOwner= viewLifecycleOwner
            this.viewModel= this@CategoryFragment.viewModel
            this.categoryRecycler.adapter =
                CategoryAdapter(mutableListOf(), this@CategoryFragment.viewModel)
        }
        observeEvent()
    }

    private fun observeEvent() = viewModel.getProductsByCategoryId(args.categoryId)

}