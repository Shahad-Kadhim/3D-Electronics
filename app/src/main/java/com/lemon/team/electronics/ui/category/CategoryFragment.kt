package com.lemon.team.electronics.ui.category

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCategoryBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.ui.categories.CategoriesFragmentDirections
import com.lemon.team.electronics.ui.search.SearchFragmentDirections
import com.lemon.team.electronics.util.EventObserver
import com.lemon.team.electronics.util.goToFragment

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
            this.categoryName.text = args.categoryName
        }
        args.categoryId?.let { viewModel.getProductsByCategoryId(it) }
        observeEvent()
    }

    private fun observeEvent(){

        viewModel.clickItemEvent.observe(this, EventObserver{
            view?.goToFragment(CategoryFragmentDirections.actionCategoryFragmentToProductFragment(it))
        })
        viewModel.clickBackEvent.observe(this, EventObserver{
            findNavController().popBackStack()
        })

    }

}