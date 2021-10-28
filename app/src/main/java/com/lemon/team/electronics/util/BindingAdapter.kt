package com.lemon.team.electronics.util

import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.bumptech.glide.Glide


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

//
//@BindingAdapter(value = ["value"])
//fun setPikerNumber(view: HorizontalQuantitizer, value: Int?) {
//
//    view.setMinusIconColor(R.color.black)
//    view.setMinusIconBackgroundColor(R.color.black)
//    view.setPlusIconColor(R.color.black)
//    view.setPlusIconBackgroundColor(R.color.white)
//
//    if (view.value != value){
//        value?.let { view.value = it }
//    }
//}
//
//@InverseBindingAdapter(attribute = "value", event = "pikerNumberChangeEvent")
//fun getPikerNumber(view: HorizontalQuantitizer): Int? {
//    return view.value
//}
//
//@BindingAdapter("pikerNumberChangeEvent")
//fun setPikerListener(view: HorizontalQuantitizer, attChange: InverseBindingListener) {
//    view.setQuantitizerListener(object : QuantitizerListener {
//        override fun onDecrease() {
//            attChange.onChange()
//        }
//        override fun onIncrease() {
//            attChange.onChange()
//        }
//    })
//}

