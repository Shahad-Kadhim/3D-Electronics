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

@BindingAdapter(value = ["app:playVideo"])
fun playVideo(view: VideoView, path: String?){
    view.setVideoPath(path)
    view.start()
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
    Log.i("sssssssssssssSetRec" , items.toString())
    (view.adapter as BaseRecyclerAdapter<T>?)?.let{
        if(items != null){
            it.setItems(items)
        } else {
            it.setItems(emptyList())
        }
    }
}

@BindingAdapter(value = ["app:itemsForNested"])
fun setRecyclerNestedItems(view: RecyclerView, items:List<HomeItems<Any>>?){
    Log.i("sssssssssSetRecNested" , items.toString())
    (view.adapter as HomeNestedAdapter?)?.let{
        if(items != null){
            it.setItemsNested(items)
        } else {
            it.setItemsNested(emptyList())
        }
    }
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


@BindingAdapter(value = ["app:showOnSuccess"])
fun <T> showOnSuccess(view: View, state: State<T>?) {
    if (state is State.Success) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.INVISIBLE
    }
}

@BindingAdapter(value = ["app:showOnError"])
fun <T> showOnError(view: View, state: State<T>?) {
    if (state is State.Error) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.INVISIBLE
    }
}

@BindingAdapter(value = ["app:showOnLoading"])
fun <T> showOnLoading(view: View, state: State<T>?) {
    if (state is State.Loading) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.INVISIBLE
    }
}
