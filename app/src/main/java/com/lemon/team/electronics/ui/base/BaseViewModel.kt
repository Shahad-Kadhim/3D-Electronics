package com.lemon.team.electronics.ui.base

import androidx.lifecycle.*
import com.lemon.team.electronics.util.State
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

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