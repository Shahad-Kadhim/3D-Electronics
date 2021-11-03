package com.lemon.team.electronics.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.ProductsResponse
import com.lemon.team.electronics.ui.category.ProductInteractionListener
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Event
import com.lemon.team.electronics.util.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SearchViewModel: BaseViewModel(), ProductInteractionListener {

    val searchName = MutableLiveData<String>()
    var searchResult = MutableLiveData<State<ProductsResponse?>>()
    private val _clickItemEvent = MutableLiveData<Event<String>>()
    var clickItemEvent :LiveData<Event<String>>  = _clickItemEvent
    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent :LiveData<Event<Boolean>>  = _clickBackEvent

    fun onclickSearch(){
        search()
    }

    private fun search() {

        searchName.value?.let {
            collectResponse(
                Repository.getProductByName(it, 0)){ product ->
                searchResult.postValue(product)
            }
        }

    }

    fun onclickBack(){
        _clickBackEvent.postValue(Event(true))
    }

    override fun onClickProduct(productId: String) {
        _clickItemEvent.postValue(Event(productId))
    }

    override fun onClickHeart(productId: String) { }

}