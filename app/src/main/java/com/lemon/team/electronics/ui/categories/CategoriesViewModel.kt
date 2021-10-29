package com.lemon.team.electronics.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.categories.CategoriesResponseItem
import com.lemon.team.electronics.model.response.categories.LocalCategoriesResponse
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.convertToLocalCategoriesResponse


class CategoriesViewModel: BaseViewModel(), CategoriesInteractionListener{

    private val _categoryList = Repository.getCategories()
        .asLiveData()
        .map {
        it.toData()?.convertToLocalCategoriesResponse()
        }

    val categoryList: LiveData<LocalCategoriesResponse?> = _categoryList
    override fun onClickCategory(Category: CategoriesResponseItem) {

    }


}
