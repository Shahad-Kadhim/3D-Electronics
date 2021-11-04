package com.lemon.team.electronics.ui.category

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCategoryBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.goToFragment
import com.lemon.team.electronics.util.observeEvent

class CategoryFragment : BaseFragment<FragmentCategoryBinding, CategoryViewModel>() {

    override val layoutId: Int = R.layout.fragment_category
    override val viewModel: CategoryViewModel by viewModels()
    val args: CategoryFragmentArgs by navArgs()

    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentCategoryBinding =
        DataBindingUtil::inflate

    override fun setUp() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@CategoryFragment.viewModel
            categoryName = args.categoryName
            categoryRecycler.adapter =
                CategoryAdapter(mutableListOf(), this@CategoryFragment.viewModel)
        }

        viewModel.getProductsByCategoryId(args.categoryId)
        observeEvent()
    }

    private fun observeEvent() {

        viewModel.clickItemEvent.observeEvent(this) {
            view?.goToFragment(CategoryFragmentDirections.actionCategoryFragmentToProductFragment(it))
        }

        viewModel.clickBackEvent.observeEvent(this) {
            findNavController().popBackStack()
        }

    }

}