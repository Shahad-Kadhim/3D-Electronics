package com.lemon.team.electronics.ui.productDetails


import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.Product
import com.lemon.team.electronics.ui.base.BaseInteractionListener
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class ProductImageRecyclerAdapter(items:List<String>, listener: ImageInteractionListener)
    :BaseRecyclerAdapter<String>(items,listener){

    override val layoutId: Int = R.layout.item_product_image

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>
    ) =
        getItems()[oldItemPosition] == newItems[newItemPosition]
}

interface ImageInteractionListener : BaseInteractionListener {

    fun onclickImage(url:String)
}