package com.lemon.team.electronics.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.lemon.team.electronics.util.*
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.CategoryResponse
import com.lemon.team.electronics.model.domain.CategoryInfoType
import com.lemon.team.electronics.ui.base.BaseViewModel

class HomeViewModel :BaseViewModel() , HomeInteractionListener ,SliderListener{

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


    private var _clickSeeMoreForCategories = MutableLiveData<Event<Boolean>>()
    val clickSeeMoreForCategories: LiveData<Event<Boolean>> = _clickSeeMoreForCategories

    private var _clickSeeMoreForCategory = MutableLiveData<Event<CategoryInfoType>>()
    val clickSeeMoreForCategory: LiveData<Event<CategoryInfoType>> = _clickSeeMoreForCategory

    private var _clickSharedProduct = MutableLiveData<String>()
    val clickSharedProduct: LiveData<String> = _clickSharedProduct

    val categories = Repository.getCategories().asLiveData()

    val bestProduct = Repository.getRecommendedProducts().asLiveData()

    val slideProducts =Repository.getProductsByCategoryId(
        "f9d895e5-b65c-4393-92fd-52a5a4d65f3a",
        Constants.PAGE_NUMBER_ZERO
    ).asLiveData()

    val mouseCategory = Repository.getProductsByCategoryId(Constants.MOUSE_CATEGORY_ID,
        Constants.PAGE_NUMBER_ZERO, Constants.SORT_CREATE_AT).asLiveData()

    val headphoneCategory = Repository.getProductsByCategoryId(Constants.HEADPHONE_CATEGORY_ID,
        Constants.PAGE_NUMBER_ZERO, Constants.SORT_CREATE_AT).asLiveData()


    val state=MediatorLiveData<State<Any>>().apply {
        addSource(slideProducts,this@HomeViewModel::checkIfSuccess)
        addSource(categories,this@HomeViewModel::checkIfSuccess)
        addSource(bestProduct,this@HomeViewModel::checkIfSuccess)
        addSource(mouseCategory,this@HomeViewModel::checkIfSuccess)
        addSource(headphoneCategory,this@HomeViewModel::checkIfSuccess)
    }


    private fun <T>checkIfSuccess(currentState:State<T>){
        if(currentState is State.Success){
            state.postValue(currentState as State<Any>)
        }
        takeIf { currentState is State.Error }?.let {
            if(ifAllStateError()){
                state.postValue(State.Error("No Internet Connection"))
            }
        }
    }

    private fun ifAllStateError() =
        checkState(
            slideProducts.value,
            categories.value,
            bestProduct.value,
            mouseCategory.value
        )


    private fun <T>checkState(vararg state:State<T>?) = state.all { it is State.Error }

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

    override fun onClickShare(productId: String) {
        _clickSharedProduct.postValue(productId)
    }

    override fun onClickCategory(categoryId: CategoryResponse) {
        _onclickCategoryEvent.postValue(Event(categoryId))
    }

    override fun onClickSeeMoreForCategories() {
        _clickSeeMoreForCategories.postValue(Event(true))
    }

    override fun onClickSeeMoreForCategory(category: CategoryInfoType) {
        _clickSeeMoreForCategory.postValue(Event(category))
    }

    override fun onclickSlider(position: Int) {
        Log.i(Constants.LOG_TAG,"$position")
    }

    override fun onclick(position: Int) {
        Log.i(Constants.LOG_TAG,"$position")
    }


}
