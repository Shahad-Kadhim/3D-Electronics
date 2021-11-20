package com.lemon.team.electronics.ui.about

import android.view.*
import androidx.navigation.fragment.findNavController
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentAboutBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.observeEvent

class AboutFragment: BaseFragment<FragmentAboutBinding,AboutViewModel>() {

    override val useActivityViewModel = false

    override val layoutId: Int = R.layout.fragment_about
    override val viewModelClass = AboutViewModel::class.java


    override fun setUpBinding() {
        binding.apply {
            companiesRecycler.adapter =
                CompaniesRecyclerAdapter(mutableListOf(),this@AboutFragment.viewModel)
            otherCompaniesRecycler.adapter =
                CompaniesRecyclerAdapter(mutableListOf(),this@AboutFragment.viewModel)
        }
    }

    override fun observeEvents(){
        viewModel.clickBackEvent.observeEvent(this){
            findNavController().navigateUp()
        }
    }

}