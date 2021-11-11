package com.lemon.team.electronics.ui.category

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCategoryBinding
import com.lemon.team.electronics.ui.CategoryRecyclerAdapter
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.goToFragment
import com.lemon.team.electronics.util.observeEvent

class CategoryFragment : BaseFragment<FragmentCategoryBinding, CategoryViewModel>(CategoryViewModel::class.java) {

    override val layoutId: Int = R.layout.fragment_category
    private val args: CategoryFragmentArgs by navArgs()

    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentCategoryBinding =
        DataBindingUtil::inflate

    override fun setUp() {
        super.setUp()
        viewModel.getProductsByCategoryId(args.categoryId)
    }

    override fun setUpBinding() {
        binding.apply {
            categoryName = args.categoryName
            categoryRecycler.adapter =
                CategoryRecyclerAdapter(mutableListOf(), this@CategoryFragment.viewModel)
        }
    }

    override fun observeEvents(){
        viewModel.clickItemEvent.observeEvent(this){
            view?.goToFragment(CategoryFragmentDirections.actionCategoryFragmentToProductFragment(it))
        }

        viewModel.clickBackEvent.observeEvent(this){
            findNavController().popBackStack()
        }
    }

}