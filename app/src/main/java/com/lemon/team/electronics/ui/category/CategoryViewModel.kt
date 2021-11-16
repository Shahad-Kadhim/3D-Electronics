package com.lemon.team.electronics.ui.category

import androidx.lifecycle.*
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.ProductsResponse
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.*
import kotlinx.coroutines.flow.*

class CategoryViewModel : BaseViewModel(), ProductInteractionListener{


    private val _categoryItems = MutableLiveData<State<ProductsResponse?>>()
    var items: LiveData<State<ProductsResponse?>> = _categoryItems

    private val _clickItemEvent = MutableLiveData<Event<String>>()
    var clickItemEvent: LiveData<Event<String>> = _clickItemEvent

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent: LiveData<Event<Boolean>> = _clickBackEvent

    val scroll = MutableStateFlow(0)


    lateinit var c: String
    fun onScrollLast() {
        _categoryItems.value?.toData()?.let {
            if (scroll.value < it.totalPages?.minus(1)!! && scroll.value == it.pageable?.pageNumber) {
                scroll.tryEmit(++scroll.value)
                collectResponse(
                    Repository.getProductsByCategoryId(c, scroll.value)) { state ->
                    _categoryItems.postValue(state)
                }
            }
        }
    }


    fun getProductsByCategoryId(categoryId: String) {
        c = categoryId
        collectResponse(
            Repository.getProductsByCategoryId(categoryId, scroll.value)) { state ->
            _categoryItems.postValue(state)
        }
    }


    override fun onClickProduct(productId: String) {
        _clickItemEvent.postValue(Event(productId))
    }

    override fun onClickHeart(productId: String) { }

    fun onClickBack(){
        _clickBackEvent.postValue(Event(true))
    }
}
