package com.lemon.team.electronics.ui.about

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentAboutBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.EventObserver

class AboutFragment:BaseFragment<FragmentAboutBinding,AboutViewModel>() {
    override val layoutId: Int = R.layout.fragment_about
    override val viewModel: AboutViewModel by viewModels()
    override val bindingInflater
        : (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentAboutBinding =
            DataBindingUtil::inflate


    override fun setUp() {
        binding.apply {
            this.lifecycleOwner= viewLifecycleOwner
            this.viewModel= this@AboutFragment.viewModel

            this.companiesRecycler.adapter =
                AboutAdapter(mutableListOf(),this@AboutFragment.viewModel)

            this.otherCompaniesRecycler.adapter =
                AboutAdapter(mutableListOf(),this@AboutFragment.viewModel)
        }
        observeEvents()
    }

    private fun observeEvents(){
        viewModel.clickBackEvent.observe(this, EventObserver{
            findNavController().popBackStack()
        })
    }

}