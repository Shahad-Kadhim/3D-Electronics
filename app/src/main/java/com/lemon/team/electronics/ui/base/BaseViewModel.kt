package com.lemon.team.electronics.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.util.Constants
import com.lemon.team.electronics.util.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {

    fun <T> collectResponse(flow: Flow<State<T?>>, function: (State<T?>) -> Unit) {

        viewModelScope.launch {
            flow.flowOn(Dispatchers.IO)
                .collect {
                    function(it)
                }
        }

    }

}