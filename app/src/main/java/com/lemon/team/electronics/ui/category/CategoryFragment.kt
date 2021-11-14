package com.lemon.team.electronics.ui.category

import android.view.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCategoryBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.goToFragment
import com.lemon.team.electronics.util.observeEvent

class CategoryFragment : BaseFragment<FragmentCategoryBinding, CategoryViewModel>() {

    override val useActivityViewModel = false

    override val layoutId: Int = R.layout.fragment_category
    private val args: CategoryFragmentArgs by navArgs()
    override val viewModelClass = CategoryViewModel::class.java

    override fun setUpBinding() {
        viewModel.getProductsByCategoryId(args.categoryId)
        binding.apply {
            categoryName = args.categoryName
            categoryId = args.categoryId
            categoryRecycler.adapter =
                CategoryRecyclerAdapter(mutableListOf(), this@CategoryFragment.viewModel)
        }
    }

    override fun observeEvents(){
        viewModel.clickItemEvent.observeEvent(this){ productId ->
            view?.goToFragment(CategoryFragmentDirections.actionCategoryFragmentToProductFragment(productId))
        }

        viewModel.clickBackEvent.observeEvent(this){
            findNavController().navigateUp()
        }
    }

}