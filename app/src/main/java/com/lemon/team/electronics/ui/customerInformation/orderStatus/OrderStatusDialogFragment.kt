package com.lemon.team.electronics.ui.customerInformation.orderStatus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentOrderStatusBinding

class OrderStatusDialogFragment : DialogFragment() {

    lateinit var binding: FragmentOrderStatusBinding
    private val viewModel: OrderStatusViewModel by viewModels()
    private val args: OrderStatusDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_order_status,
                container,
                false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.status = args.orderStatus
    }
}