package com.lemon.team.electronics.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.ui.base.BaseInteractionListener
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Event

class AboutViewModel :BaseViewModel(), BaseInteractionListener{

    var companies = Repository.getVendors()
    var otherCompanies = Repository.getOtherVendors()

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent : LiveData<Event<Boolean>> = _clickBackEvent

    var video = "https://www.3d-iraq.com/static/media/vedio-3d.146c63e2.mp4"

    fun onclickBack(){
        _clickBackEvent.postValue(Event(true))
    }
}