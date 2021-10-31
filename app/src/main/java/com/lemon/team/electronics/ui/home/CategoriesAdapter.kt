package com.lemon.team.electronics.ui.home

import com.lemon.team.electronics.R
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class CategoriesAdapter(
    items: List<com.lemon.team.electronics.model.response.productsByCategoryId.Content>,
    listener: HomeInteractionListener) : BaseRecyclerAdapter<com.lemon.team.electronics.model.response.productsByCategoryId.Content>(items, listener) {

    override val layoutId: Int = R.layout.item_categories

}