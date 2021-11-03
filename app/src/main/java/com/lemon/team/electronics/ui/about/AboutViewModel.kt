package com.lemon.team.electronics.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.ui.base.BaseInteractionListener
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Constants
import com.lemon.team.electronics.util.Event

class AboutViewModel :BaseViewModel(), BaseInteractionListener{

    var companies = Repository.getCompanies()
    var otherCompanies = Repository.getOtherCompanies()

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent : LiveData<Event<Boolean>> = _clickBackEvent

    var video = Constants.VIDEO_PATH

    fun onclickBack(){
        _clickBackEvent.postValue(Event(true))
    }
}