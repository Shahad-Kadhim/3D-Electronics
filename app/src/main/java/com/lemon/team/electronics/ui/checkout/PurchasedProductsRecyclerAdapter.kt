package com.lemon.team.electronics.ui.checkout

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.data.ProductItem
import com.lemon.team.electronics.ui.base.*

class PurchasedProductsRecyclerAdapter(
    items: List<ProductItem>,
    listener: BaseInteractionListener,
) :
    BaseRecyclerAdapter<ProductItem>(items, listener) {

    override val layoutId: Int = R.layout.item_checkout

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>,
    ) = getItems()[oldItemPosition].itemId == (newItems[newItemPosition] as ProductItem).itemId

}