package com.lemon.team.electronics.ui.categories

import com.lemon.team.electronics.ui.base.BaseInteractionListener

interface CategoriesInteractionListener: BaseInteractionListener {

    fun onClickCategory(CategoryId: String)

}