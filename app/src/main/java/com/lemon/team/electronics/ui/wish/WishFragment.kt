package com.lemon.team.electronics.ui.wish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentWishBinding
import com.lemon.team.electronics.ui.base.BaseFragment

class WishFragment:BaseFragment<FragmentWishBinding,WishViewModel>() {
    override val layoutId: Int = R.layout.fragment_wish
    override val viewModel: WishViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentWishBinding
        =DataBindingUtil::inflate

    override fun setUp() {

    }

}