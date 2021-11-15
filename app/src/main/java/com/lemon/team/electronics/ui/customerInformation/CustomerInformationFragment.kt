package com.lemon.team.electronics.ui.customerInformation

import android.widget.ArrayAdapter
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCustomerInformationBinding
import com.lemon.team.electronics.ui.base.BaseFragment

class CustomerInformationFragment :
    BaseFragment<FragmentCustomerInformationBinding, CustomerInformationViewModel>() {

    override val layoutId = R.layout.fragment_customer_information
    override val viewModelClass = CustomerInformationViewModel::class.java

    override fun observeEvents() {

    }

    override fun setUpBinding() {
        initSpinner()
    }

    private fun initSpinner() {
        val itemList = context?.resources?.getStringArray(R.array.city)
        val spinnerAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item, itemList!!)
        binding.governorateInput.apply {
            adapter = spinnerAdapter
            onItemSelectedListener = viewModel
        }
    }
}