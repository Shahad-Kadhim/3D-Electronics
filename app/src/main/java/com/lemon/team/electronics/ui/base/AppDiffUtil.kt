package com.lemon.team.electronics.ui.base

import androidx.recyclerview.widget.DiffUtil

class AppDiffUtil<T>(
    private val oldList: List<T>,
    private val newList: List<T>,
    val function : (Int, Int, List<T>) -> Boolean
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        function(oldItemPosition,newItemPosition, newList)

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = true

}