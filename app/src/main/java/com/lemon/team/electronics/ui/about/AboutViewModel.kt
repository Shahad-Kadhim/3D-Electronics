package com.lemon.team.electronics.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lemon.team.electronics.model.repository.Repository
import com.lemon.team.electronics.ui.base.*
import com.lemon.team.electronics.util.*


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