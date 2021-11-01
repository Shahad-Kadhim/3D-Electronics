package com.lemon.team.electronics.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.categories.CategoriesResponseItem
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Event


class CategoriesViewModel: BaseViewModel(), CategoriesInteractionListener{

    val categoryList = Repository.getCategories().asLiveData()

    private val _categoryId = MutableLiveData<Event<CategoriesResponseItem>>()
    val categoryId : LiveData<Event<CategoriesResponseItem>> =  _categoryId

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent :LiveData<Event<Boolean>>  = _clickBackEvent



    override fun onClickCategory(CategoryItem: CategoriesResponseItem){
        _categoryId.postValue(Event(CategoryItem))
    }

    fun onclickBack(){
        _clickBackEvent.postValue(Event(true))
    }
}
