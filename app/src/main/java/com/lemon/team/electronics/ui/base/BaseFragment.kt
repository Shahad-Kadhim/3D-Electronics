package com.lemon.team.electronics.ui.base

import android.os.Bundle
import android.view.*
import androidx.databinding.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lemon.team.electronics.BR

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    open val useActivityViewModel: Boolean = true

    abstract val layoutId: Int
    lateinit var viewModel: VM
    abstract val viewModelClass: Class<VM>
    private lateinit var _binding: VB
    val binding: VB
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        initViewModel()
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        _binding.apply {
            lifecycleOwner = this@BaseFragment
            setVariable(BR.viewModel, viewModel)
            return root
        }
    }

    private fun initViewModel() {
        viewModel = if (useActivityViewModel) {
            ViewModelProvider(requireActivity())[viewModelClass]
        } else {
            ViewModelProvider(this)[viewModelClass]
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    open fun setUp(){
        setUpBinding()
        observeEvents()
    }

    abstract fun observeEvents()

    abstract fun setUpBinding()
}