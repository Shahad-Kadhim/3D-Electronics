package com.lemon.team.electronics.ui.image

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Event

class ImageViewModel :BaseViewModel() {

    private var _onclickBack = MutableLiveData<Event<Boolean>>()
    val onclickBack : LiveData<Event<Boolean>> = _onclickBack

    fun onclickBack(){
        _onclickBack.postValue(Event(true))
    }

}