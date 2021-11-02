package com.lemon.team.electronics.ui.categories

import com.lemon.team.electronics.model.response.categories.CategoriesResponseItem
import com.lemon.team.electronics.ui.base.BaseInteractionListener

interface CategoriesInteractionListener: BaseInteractionListener {

    fun onClickCategory(CategoryId: CategoriesResponseItem)

}