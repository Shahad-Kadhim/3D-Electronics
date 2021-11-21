package com.lemon.team.electronics.util

import android.content.Context
import android.media.MediaParser
import android.text.Html
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.card.MaterialCardView
import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.HomeImage
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter
import com.lemon.team.electronics.ui.home.HomeInteractionListener
import it.sephiroth.android.library.numberpicker.NumberPicker
import it.sephiroth.android.library.numberpicker.doOnProgressChanged
import android.widget.AdapterView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.doOnTextChanged
import com.lemon.team.electronics.ui.customerInformation.orderStatus.OrderStatus
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemService


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

@BindingAdapter(value = ["app:showWhenThereIsItem"])
fun <T> showWhenThereIsItem(view: View, value: List<T>?) {
    view.isVisible = value?.isEmpty() == false
}

@BindingAdapter(value = ["app:hiddenOnState"])
fun <T> hiddenWhenState(view: View, state: State<T>?) {
    view.isVisible = (state !is State)
}


@BindingAdapter(value = ["app:showOnLoadingNew"])
fun <T> showOnLoadingNew(view: View, state: State<T>?) {
    view.visibility =
        if (state is State.Loading) {
            View.VISIBLE
        } else {
            View.GONE
        }

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

@BindingAdapter(value = ["app:last"])
fun getMoreProducts(view: RecyclerView, scroll: () -> Unit) {
    view.setOnScrollChangeListener { _, i, i2, i3, i4 ->
        if (
            (view.layoutManager as GridLayoutManager)
                .findLastCompletelyVisibleItemPosition()
            == (view.adapter?.itemCount?.minus(1))
        ) {
            scroll()
        }
    }
}



@BindingAdapter(value = ["value"])
fun setPikerNumber(view: NumberPicker, value: Int?) {
    if (view.progress != value)
        value?.let { view.progress = it }

}

@InverseBindingAdapter(attribute = "value", event = "pikerNumberChangeEvent")
fun getPikerNumber(view: NumberPicker): Int? {
    return view.progress
}

@BindingAdapter("pikerNumberChangeEvent")
fun setPikerListener(view: NumberPicker, attChange: InverseBindingListener) {
    view.doOnProgressChanged { _, _, _ ->
        attChange.onChange()
    }
}


@BindingAdapter(value = ["selectedItem"], requireAll = false)
fun bindSpinnerData(
    spinner: Spinner,
    newSelectedValue: String?,
) {
    newSelectedValue?.let {
        val pos = spinner.selectedItemPosition
        spinner.setSelection(pos, true)
    }
}

@InverseBindingAdapter(attribute = "selectedItem", event = "selectedItemAttrChanged")
fun captureSelectedValue(spinner: Spinner): String {
    return spinner.selectedItem.toString()
}

@BindingAdapter("selectedItemAttrChanged")
fun onChange(spinner: Spinner, attChange: InverseBindingListener){
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
            attChange.onChange()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
}

@BindingAdapter(value = ["app:statusImage"])
fun setStatusImage(view: ImageView, status: OrderStatus?){
    when(status){
        OrderStatus.Success -> view.setImageResource(R.drawable.order_status_success)
        OrderStatus.Fail -> view.setImageResource(R.drawable.order_status_fail)
    }
}

@BindingAdapter(value = ["app:statusTitle"])
fun setOrderStatusTitle(view: TextView, status: OrderStatus?){
    when(status){
        OrderStatus.Success -> {
            view.text = view.context.getString(R.string.success)
            view.setTextColor(view.context.getColor(R.color.order_status_success))
        }
        OrderStatus.Fail -> {
            view.text = view.context.getString(R.string.fail)
            view.setTextColor(view.context.getColor(R.color.order_status_fail))
        }
    }
}

@BindingAdapter(value = ["app:statusBodyText"])
fun setOrderStatusBodyText(view: TextView, status: OrderStatus?){
    when(status){
        OrderStatus.Success -> view.text = view.context.getString(R.string.your_order_have_been_successfully_received)
        OrderStatus.Fail -> view.text = view.context.getString(R.string.an_error_has_occurred)
    }
}

@BindingAdapter(value = ["app:statusButtonColor"])
fun setOrderButtonColor(view: AppCompatButton, status: OrderStatus?){
    when(status){
        OrderStatus.Success -> view.background = ContextCompat.getDrawable(view.context, R.drawable.shape_button_status_success)
        OrderStatus.Fail -> view.background = ContextCompat.getDrawable(view.context, R.drawable.shape_button_status_fail)
    }
}

@BindingAdapter(value = ["app:followOrderVisibility"])
fun setFollowOrderVisibility(view: View, status: OrderStatus?){
    when(status){
        OrderStatus.Success -> view.visibility = View.VISIBLE
        OrderStatus.Fail -> view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:statusButtonText"])
fun setDialogButtonText(view: AppCompatButton, status: OrderStatus?) {
    when(status){
        OrderStatus.Success -> view.text = view.context.getString(R.string.ok)
        OrderStatus.Fail -> view.text = view.context.getString(R.string.close)
    }
}

@BindingAdapter(value = ["app:errorMessage"])
fun setErrorMessage(view: EditText, message: String) {
    view.doOnTextChanged { text, _, _, _ ->
        if (text?.isEmpty() == true){
            view.error = message
        }
    }
}

@BindingAdapter(value = ["app:phoneNumberErrorMessage"])
fun setPhoneNumberErrorMessage(view: EditText, message: String) {
    view.doOnTextChanged { text, _, _, _ ->
        if (text?.isEmpty() == true) {
            view.error = message
        } else if (text?.count() != Constants.VALID_NUMBER_OF_DIGIT_OF_PHONE_NUMBER) {
            view.error = view.context.getString(R.string.phone_number_should_be_11_digit)
        }
    }
}

@BindingAdapter(value = ["app:setFocus"])
fun setFocus(view: EditText, value: Boolean) {
    view.requestFocus()
    (view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}

@BindingAdapter(value = ["app:stopVideo"])
fun stopVideo(view: VideoView, value: Boolean) {
    view.setMediaController(
        MediaController(view.context)
    )
}