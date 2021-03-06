package com.lemon.team.electronics.ui.home

import com.lemon.team.electronics.R
import com.lemon.team.electronics.data.remote.response.Product
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class CategoryAdapter(
    items: List<Product>,
    val listener: HomeInteractionListener
): BaseRecyclerAdapter<Product>(items, listener) {

    override val layoutId: Int = R.layout.item_elements_categories

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>
    ) =
        getItems()[oldItemPosition].id == (newItems[newItemPosition] as Product).id
}