package com.lemon.team.electronics.ui.search

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.search.Content
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class SearchRecyclerAdapter(items: List<Content>, listener: SearchInteractionListener)
    :BaseRecyclerAdapter<Content>(items, listener) {

    override val layoutId: Int =R.layout.item_product

}