package com.lemon.team.electronics.ui.home

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.Content
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class BestSellerAdapter(
    val items: List<Content>,
    val listener: HomeInteractionListener
) : BaseRecyclerAdapter<Content>(items, listener) {

    override val layoutId: Int = R.layout.item_best_seller

}