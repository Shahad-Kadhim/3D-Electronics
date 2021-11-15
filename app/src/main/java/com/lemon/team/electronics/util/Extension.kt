package com.lemon.team.electronics.util

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.*
import androidx.navigation.*
import androidx.navigation.fragment.FragmentNavigator
import com.lemon.team.electronics.BR
import com.lemon.team.electronics.model.data.ProductItem
import com.lemon.team.electronics.model.response.Product
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter
import java.io.IOException


fun View.goToFragment(navDir: NavDirections) {
    Navigation.findNavController(this).navigate(navDir)
}

fun View.goToFragmentWithTransition(navDir: NavDirections,extra: FragmentNavigator.Extras) {
    Navigation.findNavController(this).navigate(navDir,extra)
}

fun Context.readJsonAsset(fileName: String): String? {
    return try {
        assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        null
    }
}


fun BaseRecyclerAdapter.ItemViewHolder.setVariableAdapter(item: Any?) {
    this.binding.setVariable(BR.adapter, item)
}


fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, function:(T) ->Unit){
    this.observe(owner, EventObserver{ it ->
        function(it)
    })
}

fun Intent.sharingUrl(url: String?): Intent? {
    return  this.apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "product link")
        putExtra(Intent.EXTRA_TEXT, Constants.URL_PRODUCT_WEBSITE +url)
    }
}

fun Product.convertToItem(productType: Int, pieces: Int?) =
    ProductItem(
        0,
        id,
        name,
        oldPrice,
        sold,
        mainImage,
        pieces?.let { price?.times(it.toDouble())},
        sale,
        productType,
        pieces
    )

fun <T> List<T>.getSixItems() = this.take(6)
