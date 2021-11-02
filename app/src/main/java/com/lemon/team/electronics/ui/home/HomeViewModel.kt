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

class HomeViewModel :BaseViewModel() , HomeInteractionListener {

    private var _cartEvent = MutableLiveData<Event<Boolean>>()
    val cartEvent: LiveData<Event<Boolean>> = _cartEvent

    private var _aboutEvent = MutableLiveData<Event<Boolean>>()
    val aboutEvent: LiveData<Event<Boolean>> = _aboutEvent

    val categories = Repository.getCategories().asLiveData()
    val bestProduct = MutableLiveData<State<ProductResponse?>>()
    val mouseCategories = Repository.getProductsByCategoryId("54653fdb-db67-4e72-8840-1d842e3c4f04" ,
        0 ,  "createdAt" ).asLiveData()


    fun onClickCart(){
        _cartEvent.postValue(Event(true))
    }

    fun onClickAbout(){
        _aboutEvent.postValue(Event(true))
    }

}
