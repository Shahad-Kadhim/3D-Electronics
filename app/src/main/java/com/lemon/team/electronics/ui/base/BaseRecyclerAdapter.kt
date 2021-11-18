package com.lemon.team.electronics.ui.base

import android.annotation.SuppressLint
import android.util.Log
import android.view.*
import androidx.databinding.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lemon.team.electronics.BR

@SuppressLint("NotifyDataSetChanged")
abstract class BaseRecyclerAdapter<T>(
    private var items: List<T>,
    private val listener: BaseInteractionListener
): RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder>(){

    abstract val layoutId: Int

    abstract fun <T> areItemsTheSame(oldItemPosition: Int, newItemPosition: Int, newItems: List<T>) : Boolean

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder =
        ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        if (holder is ItemViewHolder && items.isNotEmpty() ) {
                holder.binding.setVariable(BR.item, items[position])
                holder.binding.setVariable(BR.listener, listener)
        }
    }

    override fun getItemCount(): Int = items.size

    fun setItems(newItems: List<T>) {
        val diffResult = DiffUtil.calculateDiff(AppDiffUtil(items, newItems, ::areItemsTheSame))
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    fun getItems() = items

    abstract class BaseViewHolder(binding: ViewDataBinding)
        : RecyclerView.ViewHolder(binding.root)

    open class ItemViewHolder(val binding: ViewDataBinding)
        : BaseViewHolder(binding)

}

interface BaseInteractionListener
