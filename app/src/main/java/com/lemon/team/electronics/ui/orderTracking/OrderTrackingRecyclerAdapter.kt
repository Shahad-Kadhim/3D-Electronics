package com.lemon.team.electronics.ui.orderTracking

import com.lemon.team.electronics.R
import com.lemon.team.electronics.data.remote.orderTracking.OrderTrackingResponse
import com.lemon.team.electronics.ui.base.*

class OrderTrackingRecyclerAdapter(
    items: List<OrderTrackingResponse>,
    listener: OrderInteractionListener
): BaseRecyclerAdapter<OrderTrackingResponse>(items, listener) {
    override val layoutId: Int = R.layout.item_order_tracking

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>
    ) =
        getItems()[oldItemPosition].createdAt == (newItems[newItemPosition] as OrderTrackingResponse).createdAt
}

interface OrderInteractionListener : BaseInteractionListener