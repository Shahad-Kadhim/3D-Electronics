package com.lemon.team.electronics.ui.base

import android.view.*
import androidx.databinding.*
import androidx.recyclerview.widget.RecyclerView
import com.lemon.team.electronics.BR

abstract class BaseRecyclerAdapter<T>(private var items: List<T>, private var listener: BaseInteractionListener)
    : RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder>() {


    abstract val layoutId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), layoutId, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = items[position]
        when(holder){
            is ItemViewHolder -> {
                holder.binding.apply {
                    setVariable(BR.item,currentItem)
                    setVariable(BR.listener, listener)
                }
            }
        }
    }


    fun setItems(newItems: List<T>) {
        items = newItems
        notifyDataSetChanged()
    }

    fun getItems() = items

    override fun getItemCount() = items.size


    class ItemViewHolder(val binding: ViewDataBinding): BaseViewHolder(binding)

    abstract class BaseViewHolder(binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root)

}