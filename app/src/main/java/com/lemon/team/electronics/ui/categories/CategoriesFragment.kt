package com.lemon.team.electronics.ui.categories

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCategoriesBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.*


class CategoriesFragment: BaseFragment<FragmentCategoriesBinding,CategoriesViewModel>() {

    override val layoutId: Int = R.layout.fragment_categories
    override val viewModel: CategoriesViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentCategoriesBinding
        =DataBindingUtil::inflate


    override fun setUpBinding() {
        binding.apply {
            lifecycleOwner= viewLifecycleOwner
            viewModel= this@CategoriesFragment.viewModel
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