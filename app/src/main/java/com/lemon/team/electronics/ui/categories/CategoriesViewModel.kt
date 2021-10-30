package com.lemon.team.electronics.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Event


class CategoriesViewModel: BaseViewModel(), CategoriesInteractionListener{

    val categoryList = Repository.getCategories().asLiveData()

    private val _categoryId = MutableLiveData<Event<String>>()
    val categoryId : LiveData<Event<String>> =  _categoryId



    override fun onClickCategory(CategoryId: String ){
        _categoryId.postValue(Event(CategoryId))
    }

}
