package com.lemon.team.electronics.ui.home

import androidx.lifecycle.*
import com.lemon.team.electronics.util.*
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.CategoryResponse
import com.lemon.team.electronics.model.domain.CategoryInfoType
import com.lemon.team.electronics.ui.base.BaseViewModel

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

    private var _clickSeeMoreForBestSeller = MutableLiveData<Event<Boolean>>()
    val clickSeeMoreForBestSeller: LiveData<Event<Boolean>> = _clickSeeMoreForBestSeller

    private var _clickSeeMoreForCategories = MutableLiveData<Event<Boolean>>()
    val clickSeeMoreForCategories: LiveData<Event<Boolean>> = _clickSeeMoreForCategories

    private var _clickSeeMoreForCategory = MutableLiveData<Event<CategoryInfoType>>()
    val clickSeeMoreForCategory: LiveData<Event<CategoryInfoType>> = _clickSeeMoreForCategory

    val categories = Repository.getCategories().asLiveData()

    val bestProduct = Repository.getRecommendedProducts().asLiveData()

    val slideProducts =Repository.getProductsByCategoryId(
        "f9d895e5-b65c-4393-92fd-52a5a4d65f3a",
        Constants.PAGE_NUMBER_ZERO
    ).asLiveData()

    val stateHome: MutableLiveData<State<Boolean>> = MutableLiveData(State.Loading)

    val mouseCategories = Repository.getProductsByCategoryId(Constants.MOUSE_CATEGORY_ID,
        Constants.PAGE_NUMBER_ZERO, Constants.SORT_CREATE_AT).asLiveData()

    val tripleMediatorLiveData = TripleMediatorLiveData(categories, bestProduct, slideProducts)



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
        _clickSeeMoreForBestSeller.postValue(Event(true))
    }

    override fun onClickSeeMoreForCategories() {
        _clickSeeMoreForCategories.postValue(Event(true))
    }

    override fun onClickSeeMoreForCategory(category: CategoryInfoType) {
        _clickSeeMoreForCategory.postValue(Event(category))
    }

}


class TripleMediatorLiveData<F, S, T>(
    firstLiveData: LiveData<F>,
    secondLiveData: LiveData<S>,
    thirdLiveData: LiveData<T>
) : MediatorLiveData<Triple<F?, S?, T?>>() {
    init {
        addSource(firstLiveData) { firstLiveDataValue: F -> value = Triple(firstLiveDataValue, secondLiveData.value, thirdLiveData.value) }
        addSource(secondLiveData) { secondLiveDataValue: S -> value = Triple(firstLiveData.value, secondLiveDataValue, thirdLiveData.value) }
        addSource(thirdLiveData) { thirdLiveDataValue: T -> value = Triple(firstLiveData.value, secondLiveData.value, thirdLiveDataValue) }
    }
}