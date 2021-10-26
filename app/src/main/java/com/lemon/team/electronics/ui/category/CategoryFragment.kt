package com.lemon.team.electronics.ui.category

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCategoryBinding
import com.lemon.team.electronics.ui.base.BaseFragment

class CategoryFragment:BaseFragment<FragmentCategoryBinding, CategoryViewModel>() {
    override val layoutId: Int= R.layout.fragment_category
    override val viewModel: CategoryViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentCategoryBinding
        =DataBindingUtil::inflate

    override fun setUp() {

    }

}