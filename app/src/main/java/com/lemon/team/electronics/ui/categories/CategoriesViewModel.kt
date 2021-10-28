package com.lemon.team.electronics.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.categories.CategoriesResponse
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.State


class CategoriesViewModel: BaseViewModel(){

    private val _categoryList = Repository.getCategories().asLiveData()
    val categoryList: LiveData<State<CategoriesResponse?>> = _categoryList

}
