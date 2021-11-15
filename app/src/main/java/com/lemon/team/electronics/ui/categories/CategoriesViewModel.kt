package com.lemon.team.electronics.ui.categories

import androidx.lifecycle.*
import com.lemon.team.electronics.model.repository.Repository
import com.lemon.team.electronics.model.response.CategoryResponse
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Event


class CategoriesViewModel: BaseViewModel(), CategoryInteractionListener {

    val categoryList = Repository.getCategories().asLiveData()

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
