package com.lemon.team.electronics.ui.categories

import androidx.lifecycle.*
import com.lemon.team.electronics.data.ElectronicRepository
import com.lemon.team.electronics.data.remote.response.CategoryResponse
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val repository: ElectronicRepository
): BaseViewModel(), CategoryInteractionListener {

    val categoryList = repository.getCategories().asLiveData()

    private val _categoryId = MutableLiveData<Event<CategoryResponse>>()
    val categoryId : LiveData<Event<CategoryResponse>> =  _categoryId

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent :LiveData<Event<Boolean>>  = _clickBackEvent


    override fun onClickCategory(categoryId: CategoryResponse){
        _categoryId.postValue(Event(categoryId))
    }

    fun onclickBack(){
        _clickBackEvent.postValue(Event(true))
    }

}
