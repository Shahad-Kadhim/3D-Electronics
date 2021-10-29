package com.lemon.team.electronics.ui.categories

 import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCategoriesBinding
import com.lemon.team.electronics.ui.base.BaseFragment


class CategoriesFragment:BaseFragment<FragmentCategoriesBinding,CategoriesViewModel>() {

    override val layoutId: Int = R.layout.fragment_categories
    override val viewModel: CategoriesViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentCategoriesBinding
        =DataBindingUtil::inflate

    override fun setUp() {
        binding.apply {
            this.lifecycleOwner= viewLifecycleOwner
            this.viewModel= this@CategoriesFragment.viewModel
            this.categoriesRecycler.adapter =
                CategoriesAdapter(mutableListOf(),this@CategoriesFragment.viewModel)
        }
    }

}