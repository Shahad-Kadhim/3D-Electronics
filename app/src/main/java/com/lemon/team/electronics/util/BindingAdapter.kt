package com.lemon.team.electronics.util

import android.annotation.SuppressLint
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.card.MaterialCardView
import com.lemon.team.electronics.R
import com.lemon.team.electronics.generated.callback.OnClickListener
import com.lemon.team.electronics.model.response.Product
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter
import android.widget.Toast

import android.content.Intent

import android.widget.ImageButton
import com.denzcoskun.imageslider.constants.ActionTypes
import com.denzcoskun.imageslider.interfaces.TouchListener
import com.lemon.team.electronics.ui.home.HomeInteractionListener
import com.lemon.team.electronics.ui.home.SliderListener


@BindingAdapter(value = ["app:htmlText"])
fun parseHtml(view: TextView, text: String?) {
    text?.let {
        view.text = Html.fromHtml(text).trim()
    }
}

@BindingAdapter(value = ["app:video"])
fun playVideo(view: VideoView, path: String?) {
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
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    items?.let { (view.adapter as BaseRecyclerAdapter<T>?)?.setItems(it) }
}

@BindingAdapter(value = ["app:onclickSearch"])
fun onclickSearch(view: EditText, function: () -> Unit) {
    view.setOnEditorActionListener { _, _, _ ->
        function()
        return@setOnEditorActionListener false
    }
}

@BindingAdapter(value = ["app:isVisible"])
fun showIsVisible(view: View, value: Boolean) {
    view.isVisible = (value)
}

@BindingAdapter(value = ["app:showOnSuccess"])
fun <T> showOnSuccess(view: View, state: State<T>?) {
    view.isVisible = (state is State.Success)
}

@BindingAdapter(value = ["app:showOnError"])
fun <T> showOnError(view: View, state: State<T>?) {

    Log.i("hhhhhhhhhWhenError" , state.toString())
    view.isVisible = (state is State.Error)
}

@BindingAdapter(value = ["app:showOnLoading"])
fun <T> showOnLoading(view: View, state: State<T>?) {
    view.isVisible = (state is State.Loading)
}

@BindingAdapter(value = ["app:showWhenEmpty"])
fun <T> showWhenEmpty(view: View, value: List<T>?) {
    view.isVisible = value?.isEmpty() == true
}

@BindingAdapter(value = ["app:hiddenOnState"])
fun <T> hiddenWhenState(view: View, state: State<T>?) {
    view.isVisible = (state !is State)
}

@BindingAdapter(value = ["app:setBackgroundColorItem"])
fun setBackgroundColor(view: MaterialCardView, color: ColorsRecycler?) {
    color?.let {
        view.setCardBackgroundColor(
            ContextCompat.getColor(
                view.context,
                when (color) {
                    ColorsRecycler.COLOR_ONE -> R.color.brand_color
                    ColorsRecycler.COLOR_TWO -> R.color.color_two
                    ColorsRecycler.COLOR_THREE -> R.color.color_three
                }
            )
        )
    }
}

@BindingAdapter(value = ["app:setIcon"])
fun setCategoryIcon(view: ImageView, categoryId: String?) {
    categoryId?.convertToCategoryIcon()?.let { idIcon ->
        view.setImageDrawable(
            ContextCompat.getDrawable(
                view.context,
                idIcon
            )
        )
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter(value = ["app:setText"])
fun setText(view: TextView, categoryName: String?) {
    categoryName?.let { idName ->
        view.text = idName.first() + idName.lowercase().substring(1)
    }
}

@BindingAdapter(value = ["app:setSliderImagesList"])
fun setSliderImages(slider: ImageSlider, images: List<Product>?){
    images?.map     {
        SlideModel(it.mainImage)
    }?.let { slider.setImageList(it, ScaleTypes.CENTER_INSIDE) }
}

@BindingAdapter(value=["app:itemClick"])
fun onclick(view:ImageSlider , listener: SliderListener?) {

    Log.i("LEMON_TEAMK","1111")
    view.setItemClickListener(object : ItemClickListener {
        override fun onItemSelected(position: Int) {
            Log.i("LEMON_TEAMS","1111")
            listener?.onclick(position)
        }
    })
    view.setTouchListener(object :TouchListener{
        override fun onTouched(touched: ActionTypes) {
            Log.i("LEMON_TEAMT","1111")
        }
    })
    view.setOnClickListener {
        Log.i("LEMON_TEAMN","1111")
    }
}

@BindingAdapter(value = ["app:showLoading"])
fun <T> loading(view: View, state: State<T>?) {
    view.visibility=if(state ==null){
        View.VISIBLE
    }else {
        View.GONE
    }
}