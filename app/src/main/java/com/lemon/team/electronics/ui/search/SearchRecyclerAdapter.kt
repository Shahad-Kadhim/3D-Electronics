package com.lemon.team.electronics.ui.search

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.Product
import com.lemon.team.electronics.ui.category.ProductInteractionListener
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class SearchRecyclerAdapter(items: List<Product>, listener: ProductInteractionListener)
    :BaseRecyclerAdapter<Product>(items, listener) {

    override val layoutId: Int =R.layout.item_product

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>
    ) =
        getItems()[oldItemPosition].id == (newItems[newItemPosition] as Product).id


}