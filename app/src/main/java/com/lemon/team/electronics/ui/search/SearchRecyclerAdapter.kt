package com.lemon.team.electronics.ui.search

import androidx.recyclerview.widget.DiffUtil
import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.Product
import com.lemon.team.electronics.ui.base.AppDiffUtil
import com.lemon.team.electronics.ui.category.ProductInteractionListener
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter
import com.lemon.team.electronics.ui.home.HomeItem

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


    fun addItem(newItems: List<Product>) {
        val newItemsList = products.apply {
            addAll(newItems)
        }
        val diffResult =
            DiffUtil.calculateDiff(AppDiffUtil(products, newItemsList, ::areItemsTheSame))
        diffResult.dispatchUpdatesTo(this)
    }

}