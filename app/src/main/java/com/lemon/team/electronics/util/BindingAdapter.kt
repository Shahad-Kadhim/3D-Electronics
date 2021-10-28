package com.lemon.team.electronics.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

@BindingAdapter(value = ["app:items"])
fun <T>setRecyclerItems(view: RecyclerView, items:List<T>?){
    (view.adapter as BaseRecyclerAdapter<T>?)?.let{
        if(items != null){
            it.setItems(items)
        } else {
            it.setItems(emptyList())
        }
    }

}