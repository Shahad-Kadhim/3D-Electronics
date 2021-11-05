package com.lemon.team.electronics.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter
import android.text.Html
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.lemon.team.electronics.R


@BindingAdapter(value = ["app:htmlText"])
fun parseHtml(view: TextView, text: String?) {
    text?.let {
        view.text = Html.fromHtml(text)
    }
}

@BindingAdapter(value = ["app:video"])
fun playVideo(view: VideoView, path: String?){
    view.setVideoPath(path)
    view.start()
}

@BindingAdapter(value = ["app:imageFromUrl"])
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
    items?.let { (view.adapter as BaseRecyclerAdapter<T>?)?.setItems(it) }
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
    view.isVisible = (value)
}


@BindingAdapter(value = ["app:showOnSuccess"])
fun <T> showOnSuccess(view: View, state: State<T>?) {
    view.isVisible =  (state is State.Success)
}

@BindingAdapter(value = ["app:showOnError"])
fun <T> showOnError(view: View, state: State<T>?) {
   view.isVisible =  (state is State.Error)
}

@BindingAdapter(value = ["app:showOnLoading"])
fun <T> showOnLoading(view: View, state: State<T>?) {
    view.isVisible =  (state is State.Loading)
}

@BindingAdapter(value = ["app:setBackgroundColorItem"])
fun setBackgroundColor(view:MaterialCardView , color:ColorsRecycler?){
    color?.let {
        view.setCardBackgroundColor(
            ContextCompat.getColor(view.context,
                when(color){
                    ColorsRecycler.COLOR_ONE -> R.color.brand_color
                    ColorsRecycler.COLOR_TWO -> R.color.color_two
                    ColorsRecycler.COLOR_THREE -> R.color.color_three
                }
            )
        )
    }

}