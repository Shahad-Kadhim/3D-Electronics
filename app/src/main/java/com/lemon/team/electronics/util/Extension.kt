package com.lemon.team.electronics.util

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import androidx.navigation.*
import androidx.navigation.fragment.FragmentNavigator
import com.lemon.team.electronics.BR
import com.lemon.team.electronics.R
import com.lemon.team.electronics.data.local.CartItem
import com.lemon.team.electronics.data.local.WishItem
import com.lemon.team.electronics.data.remote.order.OrderedProduct
import com.lemon.team.electronics.data.remote.response.*
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

fun Product.toCartItemEntity(pieces: Int) =
    CartItem(
        id,
        name!!,
        oldPrice!!,
        sold!!,
        mainImage!!,
        price!!.times(pieces),
        sale!!,
        pieces
    )

fun Product.toWishItemEntity() =
    WishItem(
        id,
        name!!,
        oldPrice!!,
        sold!!,
        mainImage!!,
        price!!,
        sale!!,
    )

fun <T> List<T>.getSixItems() = this.take(6)


fun setToast(view: View?, msg: String) {
    Toast.makeText(view?.context , msg,Toast.LENGTH_SHORT).show()
}
fun ProductsResponse.hasNewPage(scrollPage: Int): Boolean {
    this.pageable?.pageNumber?.let { pageNumber ->
        this.totalPages?.let { totalPage ->
            return isScroll(totalPage, scrollPage) && ifPageable(pageNumber, scrollPage)
        }
    }
    return false
}

private fun isScroll(totalPages: Int, scrollPage: Int) = scrollPage < totalPages - 1
private fun ifPageable(currentPage: Int?, scrollPage: Int) = scrollPage == currentPage

fun List<CartItem>.toOrderedProduct(): List<OrderedProduct> =
    this.map { OrderedProduct(it.pieces, it.id) }

fun String.toImageState(): Int {
    return when (this) {
        "INPROGRESS" -> R.drawable.ic_truck_in_progress
        "RECEIVED" -> R.drawable.ic_truck_recived
        "SHIPPED" -> R.drawable.ic_truck_shipped
        "DELIVERED" -> R.drawable.ic_truck_deliveried
        else -> R.drawable.ic_truck_cancled
    }
}