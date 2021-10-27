package com.lemon.team.electronics.ui.home

import androidx.lifecycle.*
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Event

class HomeViewModel :BaseViewModel(){

    private var _cartEvent = MutableLiveData<Event<Boolean>>()
    val cartEvent: LiveData<Event<Boolean>> = _cartEvent

    private var _aboutEvent = MutableLiveData<Event<Boolean>>()
    val aboutEvent: LiveData<Event<Boolean>> = _aboutEvent


    fun onClickCart(){
        _cartEvent.postValue(Event(true))
    }

    fun onClickAbout(){
        _aboutEvent.postValue(Event(true))
    }

}
