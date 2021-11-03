package com.lemon.team.electronics.ui.about

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentAboutBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.util.observeEvent

class AboutFragment: BaseFragment<FragmentAboutBinding,AboutViewModel>() {

    override val layoutId: Int = R.layout.fragment_about
    override val viewModel: AboutViewModel by viewModels()

    override val bindingInflater
        : (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentAboutBinding =
            DataBindingUtil::inflate


    override fun setUp() {

        initialLifecycle()
        initialRecyclerAdapter()
        observeEvents()

    }


    private fun initialLifecycle(){

        binding.apply {
            lifecycleOwner= viewLifecycleOwner
            viewModel= this@AboutFragment.viewModel
        }

    }


    private fun initialRecyclerAdapter(){

        binding.apply {
            companiesRecycler.adapter =
                CompaniesAdapter(mutableListOf(),this@AboutFragment.viewModel)
            otherCompaniesRecycler.adapter =
                CompaniesAdapter(mutableListOf(),this@AboutFragment.viewModel)
        }

    }


    private fun observeEvents(){

        viewModel.clickBackEvent.observeEvent(this){
            findNavController().popBackStack()
        }

    }


}