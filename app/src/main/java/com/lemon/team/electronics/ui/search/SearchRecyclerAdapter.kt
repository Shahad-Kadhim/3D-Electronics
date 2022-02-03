package com.lemon.team.electronics.ui.search

import com.lemon.team.electronics.R
import com.lemon.team.electronics.data.remote.response.Product
import com.lemon.team.electronics.ui.category.ProductInteractionListener
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class SearchRecyclerAdapter(
    val products: MutableList<Product>,
    listener: ProductInteractionListener,
) : BaseRecyclerAdapter<Product>(products, listener) {

    override val layoutId: Int = R.layout.item_product

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>,
    ) =
        getItems()[oldItemPosition].id == (newItems[newItemPosition] as Product).id

}