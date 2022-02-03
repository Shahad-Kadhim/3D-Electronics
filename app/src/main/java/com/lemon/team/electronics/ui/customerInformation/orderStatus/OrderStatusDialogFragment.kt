package com.lemon.team.electronics.ui.customerInformation.orderStatus

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.*
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentOrderStatusBinding
import com.lemon.team.electronics.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.apply {
            status = args.orderStatus
            this.viewModel = this@OrderStatusDialogFragment.viewModel
            lifecycleOwner = this@OrderStatusDialogFragment
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setCancelable(false)
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.navigateToHome.observeEvent(this){
            when(args.orderStatus){
                OrderStatus.Success -> navigateToHome()
                OrderStatus.Fail -> findNavController().navigateUp()
            }
        }

        viewModel.navigateToFollowOrder.observeEvent(this){
            navigateToFollowOrder()
        }
    }

    private fun navigateToFollowOrder() {
        findNavController().navigate(OrderStatusDialogFragmentDirections.actionOrderStatusDialogFragmentToOrderTrackingFragment())
    }

    private fun navigateToHome() {
        val action = OrderStatusDialogFragmentDirections.actionOrderStatusDialogFragmentToHomeFragment()
        findNavController().navigate(action)
    }
}