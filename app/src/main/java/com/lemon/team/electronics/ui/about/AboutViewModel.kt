package com.lemon.team.electronics.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lemon.team.electronics.BuildConfig
import com.lemon.team.electronics.data.ElectronicRepository
import com.lemon.team.electronics.ui.base.*
import com.lemon.team.electronics.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val repository: ElectronicRepository
): BaseViewModel(), BaseInteractionListener{

    var companies = repository.getCompanies()
    var otherCompanies = repository.getOtherCompanies()

    val appVersion = BuildConfig.VERSION_NAME

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent : LiveData<Event<Boolean>> = _clickBackEvent

    var video = Constants.VIDEO_PATH

    fun onclickBack(){
        _clickBackEvent.postValue(Event(true))
    }

}