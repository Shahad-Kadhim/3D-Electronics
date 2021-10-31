package com.lemon.team.electronics.ui.home

import androidx.lifecycle.*
import com.lemon.team.electronics.model.response.categories.CategoriesResponse
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Event
import com.lemon.team.electronics.util.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

class HomeViewModel :BaseViewModel() {

    private var _cartEvent = MutableLiveData<Event<Boolean>>()
    val cartEvent: LiveData<Event<Boolean>> = _cartEvent

    private var _aboutEvent = MutableLiveData<Event<Boolean>>()
    val aboutEvent: LiveData<Event<Boolean>> = _aboutEvent

    val categories = MutableLiveData<State<CategoriesResponse?>>()

    var itemsList: MutableLiveData<List<HomeItems<Any>>> = MutableLiveData()

    suspend fun <T> collectValue(
        repoValue: Flow<State<T?>>, liveValue: MutableLiveData<State<T?>>) {
        repoValue
            .catch {  }
            .collect {  liveValue.value = it  }
    }

//    private fun getListAdapte() {
//        viewModelScope.launch {
//            collectValue(Repository.getCategories() , categories)
//
//            itemsList.value = listOf(HomeItems(null, HomeItemsType.TYPE_SLIDE_SHOW , ""),
//                HomeItems(null, HomeItemsType.TYPE_SEARCH , ""),
//                HomeItems("categories", HomeItemsType.TYPE_CATEGORIES , categories.value?.toData()!!),
//                HomeItems("best seller", HomeItemsType.TYPE_BEST_SELLER ,  emptyList<ProductResponse>()),
//                HomeItems("categories_element", HomeItemsType.TYPE_ELEMENTS_CATEGORIES , emptyList<Content>()))
//        }
//    }

    fun onClickCart(){
        _cartEvent.postValue(Event(true))
    }

    fun onClickAbout(){
        _aboutEvent.postValue(Event(true))
    }

}
