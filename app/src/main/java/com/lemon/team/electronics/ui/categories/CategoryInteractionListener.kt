package com.lemon.team.electronics.ui.categories

import com.lemon.team.electronics.ui.base.BaseInteractionListener

interface CategoryInteractionListener: BaseInteractionListener {
    fun onClickCategory(CategoryId: Int)
}