package com.lemon.team.electronics.ui.category

import com.lemon.team.electronics.R
import com.lemon.team.electronics.data.remote.response.Product
import com.lemon.team.electronics.ui.base.BaseInteractionListener
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class CategoryRecyclerAdapter(
    items: List<Product>,
    listener: ProductInteractionListener
): BaseRecyclerAdapter<Product>(items, listener) {
    override val layoutId: Int = R.layout.item_product

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>
    ) =
        getItems()[oldItemPosition].id == (newItems[newItemPosition] as Product).id
}

interface ProductInteractionListener: BaseInteractionListener {
    fun onClickProduct(productId : String)
}