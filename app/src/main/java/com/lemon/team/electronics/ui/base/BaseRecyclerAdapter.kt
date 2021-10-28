package com.lemon.team.electronics.ui.base

import androidx.databinding.*
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T>(private var items: List<T>, private var listener: BaseInteractionListener)
    : RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder>() {

    fun setItems(newItems: List<T>) {
        items = newItems
    }

    fun getItems() = items

    override fun getItemCount() = items.size


    abstract class BaseViewHolder(binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root)

}