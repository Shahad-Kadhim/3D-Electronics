package com.lemon.team.electronics.ui.home

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentHomeBinding
import com.lemon.team.electronics.ui.base.BaseFragment

class HomeFragment:BaseFragment<FragmentHomeBinding,HomeViewModel>() {
    override val layoutId: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentHomeBinding
        =DataBindingUtil::inflate

    override fun setUp() {

    }

}