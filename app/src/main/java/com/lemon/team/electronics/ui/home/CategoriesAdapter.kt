package com.lemon.team.electronics.ui.home

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.CategoryResponse
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class CategoriesAdapter(
    items: List<CategoryResponse>,
    val listener: HomeInteractionListener
) : BaseRecyclerAdapter<CategoryResponse>(items, listener) {
    override val layoutId: Int = R.layout.item_categories
}
