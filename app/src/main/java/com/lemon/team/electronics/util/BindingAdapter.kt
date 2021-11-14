package com.lemon.team.electronics.util

import android.text.Html
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
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
import com.lemon.team.electronics.model.response.CategoryResponse
import com.lemon.team.electronics.model.response.HomeImage
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

import com.lemon.team.electronics.ui.home.HomeInteractionListener


@BindingAdapter(value = ["app:htmlText"])
fun parseHtml(view: TextView, text: String?) {
    text?.let { stringText ->
        view.text = Html.fromHtml(stringText).trim()
    }
}

@BindingAdapter(value = ["app:video"])
fun playVideo(view: VideoView, path: String?) {
    view.setVideoPath(path)
    view.start()
}

@BindingAdapter(value = ["app:imageFromUrl"])
fun setImage(view: ImageView, url: String?) {
    url?.let { imageUrl ->
        Glide.with(view)
            .load(imageUrl)
            .centerCrop()
            .into(view)
    }
}

@BindingAdapter(value = ["app:imageCenterInside"])
fun setImageCenterInside(view: ImageView, url: String?) {
    url?.let { imageUrl ->
        Glide.with(view)
            .load(imageUrl)
            .into(view)
    }
}

@BindingAdapter(value = ["app:checkProduct"])
fun checkStateProduct(view: View, value: Boolean) {
    view.isVisible = !value
}

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    items?.let { listItems ->
        (view.adapter as BaseRecyclerAdapter<T>?)
            ?.setItems(listItems)
    }
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
    color?.let { colorsRecycler ->
        view.setCardBackgroundColor(
            ContextCompat.getColor(
                view.context,
                when (colorsRecycler) {
                    ColorsRecycler.COLOR_ONE -> R.color.color_category_one
                    ColorsRecycler.COLOR_TWO -> R.color.color_category_two
                    ColorsRecycler.COLOR_THREE -> R.color.color_category_three
                }
            )
        )
    }
}

@BindingAdapter(value = ["app:setIcon"])
fun setCategoryIcon(view: ImageView, categoryId: String?) {
    with(CategoryMap.categoryIcon[categoryId]){
        view.setImageDrawable(ContextCompat.getDrawable(view.context, this ?: R.drawable.ic_pc))
    }
}

@BindingAdapter(value = ["app:setSliderImagesList"])
fun setSliderImages(slider: ImageSlider, images: List<HomeImage>?){
    images?.map { image ->
        SlideModel(image.productImage)
    }?.let { list ->
        slider.setImageList(list, ScaleTypes.FIT)
    }
}

@BindingAdapter(value=["app:itemClick"])
fun onClickSliderItem(view:ImageSlider , listener: HomeInteractionListener?) {
    view.setItemClickListener(object : ItemClickListener {
        override fun onItemSelected(position: Int) {
            listener?.onClickSliderItem(position)
        }
    })
}

@BindingAdapter(value = ["app:showLoading"])
fun <T> loading(view: View, state: State<T>?) {
    view.visibility=if(state ==null){
        View.VISIBLE
    }else {
        View.GONE
    }
}

@BindingAdapter(value = ["app:categoryId"])
fun setCategoryNameById(view: TextView, categoryId: String?) {
    CategoryMap.categoryName[categoryId]?.let {
        view.text = view.context.getString(it)
    }
}