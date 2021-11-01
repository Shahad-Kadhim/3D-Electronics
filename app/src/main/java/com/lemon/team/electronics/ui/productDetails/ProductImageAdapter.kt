package com.lemon.team.electronics.ui.productDetails


import com.lemon.team.electronics.R
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class ProductImageAdapter(items:List<String>,listener: ImageInteractionListener)
    :BaseRecyclerAdapter<String>(items,listener){

    override val layoutId: Int = R.layout.item_product_image
}