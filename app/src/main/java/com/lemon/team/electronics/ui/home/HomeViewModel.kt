package com.lemon.team.electronics.ui.home

import androidx.lifecycle.*
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.categories.CategoriesResponse
import com.lemon.team.electronics.model.response.productById.ProductResponse
import com.lemon.team.electronics.model.response.productsByCategoryId.Content
import com.lemon.team.electronics.model.response.productsByCategoryId.ProductsInCategoryResponse
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Event
import com.lemon.team.electronics.util.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel :BaseViewModel() {

    private var _cartEvent = MutableLiveData<Event<Boolean>>()
    val cartEvent: LiveData<Event<Boolean>> = _cartEvent

    private var _aboutEvent = MutableLiveData<Event<Boolean>>()
    val aboutEvent: LiveData<Event<Boolean>> = _aboutEvent

    var categories = MutableLiveData<State<CategoriesResponse?>>()
    var bestProduct = MutableLiveData<State<ProductResponse?>>()
    var elementCategories = MutableLiveData<State<ProductsInCategoryResponse?>>()

    var itemsList: MutableLiveData<List<HomeItems<Any?>>> = MutableLiveData()


    init {
        setListsAdapter()
    }

    private fun setListsAdapter() {
        viewModelScope.launch {
            Repository.getCategories().collect { categories.value = it }
            Repository.getProductsByCategoryId("54653fdb-db67-4e72-8840-1d842e3c4f04" , 0 , "createdAt")
                .collect { elementCategories.value = it }

            itemsList.value =
                listOf(HomeItems(null, HomeItemsType.TYPE_SLIDE_SHOW.index , ""),
                    HomeItems(null, HomeItemsType.TYPE_SEARCH.index , ""),
                    HomeItems("categories", HomeItemsType.TYPE_CATEGORIES.index ,
                        categories.value?.toData()),
                    HomeItems("best seller", HomeItemsType.TYPE_BEST_SELLER.index ,
                        emptyList<ProductResponse>()),
                    HomeItems("categories_element", HomeItemsType.TYPE_ELEMENTS_CATEGORIES.index ,
                        elementCategories.value?.toData()?.content))
        }
    }

    fun onClickCart(){
        _cartEvent.postValue(Event(true))
    }

    fun onClickAbout(){
        _aboutEvent.postValue(Event(true))
    }

}
