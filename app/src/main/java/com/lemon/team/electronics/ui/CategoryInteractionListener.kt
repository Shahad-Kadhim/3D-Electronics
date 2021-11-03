package com.lemon.team.electronics.ui

import com.lemon.team.electronics.model.response.categories.CategoriesResponseItem
import com.lemon.team.electronics.ui.base.BaseInteractionListener

interface CategoryInteractionListener: BaseInteractionListener {

    fun onClickCategory(CategoryId: CategoriesResponseItem)

}