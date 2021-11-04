package com.lemon.team.electronics.ui.search

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.Content
import com.lemon.team.electronics.ui.category.ProductInteractionListener
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class SearchRecyclerAdapter(items: List<Content>, listener: ProductInteractionListener)
    :BaseRecyclerAdapter<Content>(items, listener) {

    override val layoutId: Int =R.layout.item_product

}