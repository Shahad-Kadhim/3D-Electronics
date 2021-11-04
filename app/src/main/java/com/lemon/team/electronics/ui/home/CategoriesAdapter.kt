package com.lemon.team.electronics.ui.home

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.categories.CategoriesResponseItem
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class CategoriesAdapter(
    val items: List<CategoriesResponseItem>,
    val listener: HomeInteractionListener
) : BaseRecyclerAdapter<CategoriesResponseItem>(items, listener) {
    override val layoutId: Int = R.layout.item_categories
}
