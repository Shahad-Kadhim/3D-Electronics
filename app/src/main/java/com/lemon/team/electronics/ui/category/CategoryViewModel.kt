package com.lemon.team.electronics.ui.category

import androidx.lifecycle.*
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.categories.CategoriesResponseItem
import com.lemon.team.electronics.model.response.productsByCategoryId.ProductsInCategoryResponse
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CategoryViewModel : BaseViewModel(), CategoryInteractionListener{

    private val _categoryItems = MutableLiveData<State<ProductsInCategoryResponse?>>()
    var categoryItems : LiveData<State<ProductsInCategoryResponse?>> = _categoryItems

    private val _clickItemEvent = MutableLiveData<Event<String>>()
    var clickItemEvent :LiveData<Event<String>>  = _clickItemEvent


    fun getProductsByCategoryId(categoryId: String){
        viewModelScope.launch {
            Repository.getProductsByCategoryId(categoryId, Constants.PAGE_NUMBER)
                .flowOn(Dispatchers.IO)
                .collect {
                    _categoryItems.postValue(it)
                }
        }
    }


    override fun onClickProduct(productId: String) {
        _clickItemEvent.postValue(Event(productId))
    }

}
