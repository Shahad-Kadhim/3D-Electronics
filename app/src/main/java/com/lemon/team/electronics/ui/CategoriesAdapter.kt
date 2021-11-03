package com.lemon.team.electronics.ui

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.categories.CategoriesResponseItem
import com.lemon.team.electronics.ui.base.*

class CategoriesAdapter(
    items: List<CategoriesResponseItem>,
    listener: CategoryInteractionListener
) : BaseRecyclerAdapter<CategoriesResponseItem>(items, listener) {

    override val layoutId: Int = R.layout.item_category

}
