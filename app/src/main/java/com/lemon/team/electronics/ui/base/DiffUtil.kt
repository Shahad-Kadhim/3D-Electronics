package com.lemon.team.electronics.ui.base

import androidx.recyclerview.widget.DiffUtil

class DiffUtil<T>(private val oldList: List<T>, private val newList: List<T>) :
    DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = true

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = true

}