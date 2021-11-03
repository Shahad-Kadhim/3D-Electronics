package com.lemon.team.electronics.ui.home

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.productById.ProductResponse
import com.lemon.team.electronics.ui.ProductInteractionListener
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class BestSellerAdapter(
    items: List<ProductResponse>,
    listener: ProductInteractionListener) : BaseRecyclerAdapter<ProductResponse>(items, listener) {

    override val layoutId: Int = R.layout.item_best_seller

}