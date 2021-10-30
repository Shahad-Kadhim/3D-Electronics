package com.lemon.team.electronics.ui.categories

 import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
 import androidx.navigation.fragment.findNavController
 import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCategoriesBinding
import com.lemon.team.electronics.ui.base.BaseFragment
 import com.lemon.team.electronics.ui.search.SearchFragmentDirections
 import com.lemon.team.electronics.util.EventObserver
 import com.lemon.team.electronics.util.goToFragment


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
        observeEvent()
    }

    private fun observeEvent() {
        viewModel.categoryId.observe(this, EventObserver{
            view?.goToFragment(CategoriesFragmentDirections
                .actionCategoriesFragmentToCategoryFragment(it)
            )
        })
    }

}