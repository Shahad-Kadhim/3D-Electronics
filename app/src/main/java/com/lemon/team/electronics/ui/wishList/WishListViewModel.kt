package com.lemon.team.electronics.ui.wishList

import androidx.lifecycle.*
import com.lemon.team.electronics.data.ElectronicRepository
import com.lemon.team.electronics.data.local.CartItem
import com.lemon.team.electronics.data.local.WishItem
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val repository: ElectronicRepository
): BaseViewModel() , WishInteractionListener {

    val wishListItems = repository.getWishedProducts().asLiveData()

    private val _clickItemEvent = MutableLiveData<Event<String>>()
    var clickItemEvent : LiveData<Event<String>> = _clickItemEvent

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent :LiveData<Event<Boolean>>  = _clickBackEvent

    private var _clickAdd = MutableLiveData<Event<Boolean>>()
    val clickAdd: LiveData<Event<Boolean>> = _clickAdd

    private var _toast = MutableLiveData<Event<String>>()
    val toast: LiveData<Event<String>> = _toast


    override fun onClickProduct(productId: String) {
        _clickItemEvent.postValue(Event(productId))
    }


    override fun onclickAddToCart(productId: WishItem) {
        addCartItem(productId)
    }

    override fun onclickHeart(productId: String) {
        viewModelScope.launch {
            repository.deleteWishItemById(productId)

        }
    }

    private fun addCartItem(product: WishItem) {
        viewModelScope.launch {
            if (!isItemExists(product.id)!!) {
                repository.insertCartItem(setItem(product))
                _toast.postValue(Event("Added 1 Piece To Cart"))
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
        product?.let { repository.checkCartItemExists(it) }


    private suspend fun getPiecesNumber(product: String) {
        viewModelScope.launch {
            repository.getCartItemById(product)?.let {
                updateItem(product, it.pieces.plus(1))
                _toast.postValue(Event("Added ${it.pieces.plus(1)} Piece To Cart"))
            }
        }
    }

    private suspend fun updateItem(product: String, piecesNumber: Int) {
        viewModelScope.launch {
            repository.getCartItemById(product)
                ?.let {
                    repository.updateCartItem(
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