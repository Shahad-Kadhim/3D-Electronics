package com.lemon.team.electronics.ui.home

import androidx.lifecycle.*
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.CategoryResponse
import com.lemon.team.electronics.model.response.productsByCategoryId.CategoryInfoType
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Constants.MOUSE_CATEGORY_ID
import com.lemon.team.electronics.util.Constants.PAGE_NUMBER_ZERO
import com.lemon.team.electronics.util.Constants.SORT_CREATE_AT
import com.lemon.team.electronics.util.Event

class HomeViewModel :BaseViewModel() , HomeInteractionListener {

    private var _cartEvent = MutableLiveData<Event<Boolean>>()
    val cartEvent: LiveData<Event<Boolean>> = _cartEvent

    private var _aboutEvent = MutableLiveData<Event<Boolean>>()
    val aboutEvent: LiveData<Event<Boolean>> = _aboutEvent

    private var _searchEvent = MutableLiveData<Event<Boolean>>()
    val searchEvent: LiveData<Event<Boolean>> = _searchEvent

    private var _onclickProductEvent = MutableLiveData<Event<String>>()
    val onclickProductEvent: LiveData<Event<String>> = _onclickProductEvent

    private var _onclickCategoryEvent = MutableLiveData<Event<CategoryResponse>>()
    val onclickCategoryEvent: LiveData<Event<CategoryResponse>> = _onclickCategoryEvent

    var clickSeeMoreForBestSeller = MutableLiveData<Event<Boolean>>()
        private set

    var clickSeeMoreForCategories = MutableLiveData<Event<Boolean>>()
        private set

    var clickSeeMoreForCategory = MutableLiveData<Event<CategoryInfoType>>()
        private set

    val categories = Repository.getCategories().asLiveData()
    val bestProduct = Repository.getRecommendedProducts().asLiveData()
    val mouseCategories = Repository.getProductsByCategoryId(MOUSE_CATEGORY_ID,
        PAGE_NUMBER_ZERO, SORT_CREATE_AT).asLiveData()


    fun onClickCart(){
        _cartEvent.postValue(Event(true))
    }

    fun onClickAbout(){
        _aboutEvent.postValue(Event(true))
    }

    override fun onclickSearch() {
        _searchEvent.postValue(Event(true))
    }

    override fun onClickProduct(productId: String) {
        _onclickProductEvent.postValue(Event(productId))
    }

    override fun onClickHeart(productId: String) {
        // write code when create database
    }

    override fun onClickCategory(categoryId: CategoryResponse) {
        _onclickCategoryEvent.postValue(Event(categoryId))
    }
    override fun onClickSeeMoreForBestSeller() {
        clickSeeMoreForBestSeller.postValue(Event(true))
    }

    override fun onClickSeeMoreForCategories() {
        clickSeeMoreForCategories.postValue(Event(true))
    }

    override fun onClickSeeMoreForCategory(category: CategoryInfoType) {
        clickSeeMoreForCategory.postValue(Event(category))
    }


}
