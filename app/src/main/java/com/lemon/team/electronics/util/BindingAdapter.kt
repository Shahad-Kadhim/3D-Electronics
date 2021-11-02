package com.lemon.team.electronics.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.lemon.team.electronics.ui.home.HomeItems
import com.lemon.team.electronics.ui.home.HomeNestedAdapter


@BindingAdapter(value = ["app:parseHtmlText"])
fun parseHtml(view: TextView, text: String?) {
    text?.let {
        view.text = Html.fromHtml(text)
    }
}

@BindingAdapter(value = ["app:urlForImage"])
fun setImage(view: ImageView, url: String?) {
    url?.let {
        Glide.with(view)
            .load(url)
            .centerCrop()
            .into(view)
    }
}

@BindingAdapter(value = ["app:checkProduct"])
fun checkStateProduct(view: View, value: Boolean) {
    view.isVisible = !value
}

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

@BindingAdapter(value = ["app:itemsForNested"])
fun setRecyclerNestedItems(view: RecyclerView, items: List<HomeItems<Any?>>?) {
    items?.let { (view.adapter as HomeNestedAdapter?)?.setItemsNested(it) }
}

@BindingAdapter(value=["app:onclickSearch"])
fun onclickSearch(view:EditText , function :() -> Unit){
    view.setOnEditorActionListener { _, _, _ ->
        function()
        return@setOnEditorActionListener false
    }
}

@BindingAdapter(value=["app:isVisible"])
fun showIsVisible(view:View,value:Boolean){
    view.visibility = if(value) View.VISIBLE else View.GONE
}