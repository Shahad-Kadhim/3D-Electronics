package com.lemon.team.electronics.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.productById.ProductResponse
import com.lemon.team.electronics.model.response.search.SearchResponse
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SearchViewModel: BaseViewModel(), SearchInteractionListener{

    var searchResult = MutableLiveData<State<SearchResponse?>>()

    fun getSearchResult(productName: String,page: Int) {
        viewModelScope.launch {
            Repository.getProductByName(productName, page)
                .flowOn(Dispatchers.IO)
                .catch { }
                .collect {
                    searchResult.postValue(it)
                }
        }
    }


}