package com.lemon.team.electronics.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.ItemProductBinding
import com.lemon.team.electronics.databinding.ItemProductOutOfStockBinding
import com.lemon.team.electronics.databinding.ItemProductSaleBinding
import com.lemon.team.electronics.model.response.search.Content
import com.lemon.team.electronics.model.response.search.SearchResponse
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class SearchRecyclerAdapter(items: List<Content>, listener: SearchInteractionListener)
    :BaseRecyclerAdapter<Content>(items, listener) {
    override val layoutId: Int =R.layout.item_product_sale

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
//        when(viewType) {
//            VIEW_TYPE_PRODUCT ->{
//                ProductViewHolder(DataBindingUtil
//                    .inflate(LayoutInflater.from(parent.context),R.layout.item_product,parent,false)
//                )
//            }
//            VIEW_TYPE_SALE ->{
//                ProductOnSaleViewHolder(DataBindingUtil
//                    .inflate(LayoutInflater.from(parent.context),R.layout.item_product_sale,parent,false)
//                )
//            }
//            VIEW_TYPE_OUT_OF_STOCK ->{
//                ProductOutOfStockViewHolder(DataBindingUtil.inflate(
//                    LayoutInflater.from(parent.context),R.layout.item_product_out_of_stock,parent,false)
//                )
//            }
//        }
//        return super.createViewHolder(parent,viewType)
//    }
//
//    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
//        when(holder){
//            is ProductViewHolder -> {
//                holder.binding.apply {
//
//
//                }
//            }
//            is ProductOnSaleViewHolder -> {
//                holder.binding.apply {
//
//                }
//            }
//            is ProductOutOfStockViewHolder -> {
//                holder.binding.apply {
//
//                }
//            }
//        }
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return when (position) {
//            0 -> VIEW_TYPE_PRODUCT
//            1 -> VIEW_TYPE_SALE
//            else -> VIEW_TYPE_OUT_OF_STOCK
//        }
//    }
//
//
//    class ProductViewHolder(val binding: ItemProductBinding): BaseViewHolder(binding)
//    class ProductOnSaleViewHolder(val binding: ItemProductSaleBinding): BaseViewHolder(binding)
//    class ProductOutOfStockViewHolder(val binding: ItemProductOutOfStockBinding): BaseViewHolder(binding)
//
//    companion object {
//        const val VIEW_TYPE_PRODUCT = 0
//        const val VIEW_TYPE_SALE = 1
//        const val VIEW_TYPE_OUT_OF_STOCK = 2
//    }
//
//    override val layoutId: Int
//        get() = TODO("Not yet implemented")

}