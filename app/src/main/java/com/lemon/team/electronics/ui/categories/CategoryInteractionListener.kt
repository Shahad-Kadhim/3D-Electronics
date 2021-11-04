package com.lemon.team.electronics.ui.categories

import com.lemon.team.electronics.model.response.categories.CategoryResponse
import com.lemon.team.electronics.ui.base.BaseInteractionListener

interface CategoryInteractionListener: BaseInteractionListener {

    fun onClickCategory(categoryId: CategoryResponse)

}