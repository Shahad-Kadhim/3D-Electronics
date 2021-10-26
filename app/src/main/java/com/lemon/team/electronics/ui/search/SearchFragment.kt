package com.lemon.team.electronics.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentSearchBinding
import com.lemon.team.electronics.ui.base.BaseFragment

class SearchFragment:BaseFragment<FragmentSearchBinding,SearchViewModel>() {
    override val layoutId: Int = R.layout.fragment_search
    override val viewModel: SearchViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentSearchBinding
        =DataBindingUtil::inflate

    override fun setUp() {

    }

}