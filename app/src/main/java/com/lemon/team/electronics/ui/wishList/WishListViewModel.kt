package com.lemon.team.electronics.ui.wishList

import android.util.Log
import androidx.lifecycle.*
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.data.CartItem
import com.lemon.team.electronics.model.data.WishItem
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.*
import kotlinx.coroutines.launch

class WishListViewModel : BaseViewModel() , WishInteractionListener {

    val wishListItems = Repository.getWishedProducts().asLiveData()

    private val _clickItemEvent = MutableLiveData<Event<String>>()
    var clickItemEvent : LiveData<Event<String>> = _clickItemEvent

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent :LiveData<Event<Boolean>>  = _clickBackEvent

    private var _clickAdd = MutableLiveData<Event<Boolean>>()
    val clickAdd: LiveData<Event<Boolean>> = _clickAdd


    override fun onClickProduct(productId: String) {
        _clickItemEvent.postValue(Event(productId))
    }


    override fun onclickAddToCart(product: WishItem) {
        addCartItem(product)
    }

    override fun onclickHeart(productId: String) {
        viewModelScope.launch {
            Repository.deleteWishItemById(productId)

        }
    }

    private var _toast = MutableLiveData<Event<Int>>()
    val toast: LiveData<Event<Int>> = _toast

    private fun addCartItem(product: WishItem) {
        viewModelScope.launch {
            if (!isItemExists(product.id)!!) {
                Repository.insertCartItem(setItem(product))
                _toast.postValue(Event(1))
            } else
                getPiecesNumber(product.id)
        }
    }


    fun setItem(product: WishItem) =
        product.let {
            CartItem(
                it.id,
                it.name,
                it.oldPrice,
                it.sold,
                it.mainImage,
                it.price,
                it.sale,
                1
            )
        }

    private suspend fun isItemExists(product: String?) =
        product?.let { Repository.checkCartItemExists(it) }


    private suspend fun getPiecesNumber(product: String) {
        viewModelScope.launch {
            Repository.getCartItemById(product)?.let {
                updateItem(product, it.pieces.plus(1))
                _toast.postValue(Event(it.pieces.plus(1)))
            }
        }
    }

    private suspend fun updateItem(product: String, piecesNumber: Int) {
        viewModelScope.launch {
            Repository.getCartItemById(product)
                ?.let {
                    Repository.updateCartItem(
                        it.id,
                        piecesNumber,
                        it.price.times(piecesNumber)
                    )
                }
        }
    }


    fun onClickBack() {
        _clickBackEvent.postValue(Event(true))
    }

}