package com.lemon.team.electronics.ui.about

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentAboutBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.observeEvent

class AboutFragment: BaseFragment<FragmentAboutBinding,AboutViewModel>(AboutViewModel::class.java) {

    override val layoutId: Int = R.layout.fragment_about

    override val bindingInflater
        : (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentAboutBinding =
            DataBindingUtil::inflate

    override fun setUpBinding(){
        binding.apply {
            companiesRecycler.adapter =
                CompaniesRecyclerAdapter(mutableListOf(),this@AboutFragment.viewModel)
            otherCompaniesRecycler.adapter =
                CompaniesRecyclerAdapter(mutableListOf(),this@AboutFragment.viewModel)
        }
    }

    override fun observeEvents(){
        viewModel.clickBackEvent.observeEvent(this){
            findNavController().popBackStack()
        }
    }


}