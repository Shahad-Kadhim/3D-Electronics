package com.lemon.team.electronics.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB:ViewBinding,VM:BaseViewModel>() :Fragment(){

    abstract val layoutId: Int
    abstract val viewModel:VM
    abstract val bindingInflater : (LayoutInflater, Int, ViewGroup?, Boolean) -> VB
    private lateinit var _binding :ViewBinding
    var binding:VB= _binding as VB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    abstract fun setUp()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        bindingInflater(inflater,layoutId,container,false).apply { _binding=this }.root

}