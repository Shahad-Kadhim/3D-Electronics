package com.lemon.team.electronics.ui.base

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lemon.team.electronics.BR

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel>(private val viewModelClass: Class<VM>) : Fragment() {

    abstract val layoutId: Int
    lateinit var viewModel: VM
    abstract val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> VB
    private lateinit var _binding: VB
    val binding: VB
        get() = _binding

    private fun getViewM(): VM = ViewModelProvider(this).get(viewModelClass)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        viewModel = getViewM()
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        _binding.lifecycleOwner = this
        _binding.setVariable(BR.viewModel, viewModel)
        return _binding.root
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