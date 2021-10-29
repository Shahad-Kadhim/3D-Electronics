package com.lemon.team.electronics.ui.categories

import androidx.lifecycle.asLiveData
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.categories.CategoriesResponseItem
import com.lemon.team.electronics.ui.base.BaseViewModel


class CategoriesViewModel: BaseViewModel(), CategoriesInteractionListener{

    val categoryList = Repository.getCategories().asLiveData()

    override fun onClickCategory(Category: CategoriesResponseItem) { }

}
