package com.lemon.team.electronics.ui.home

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.categories.CategoriesResponseItem
import com.lemon.team.electronics.ui.base.BaseInteractionListener
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class CategoriesAdapter(
    items: List<CategoriesResponseItem>,
    listener: BaseInteractionListener
) : BaseRecyclerAdapter<CategoriesResponseItem>(items, listener) {

    override val layoutId: Int = R.layout.item_categories

}