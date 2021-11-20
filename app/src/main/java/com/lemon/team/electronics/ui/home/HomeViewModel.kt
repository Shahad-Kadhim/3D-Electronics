package com.lemon.team.electronics.ui.home


import androidx.lifecycle.*
import com.lemon.team.electronics.util.*
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.CategoryResponse
import com.lemon.team.electronics.model.domain.CategoryInfoType
import com.lemon.team.electronics.model.response.Product
import com.lemon.team.electronics.ui.base.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel: BaseViewModel(), HomeInteractionListener {

    private var _cartEvent = MutableLiveData<Event<Boolean>>()
    val cartEvent: LiveData<Event<Boolean>> = _cartEvent

    private var _aboutEvent = MutableLiveData<Event<Boolean>>()
    val aboutEvent: LiveData<Event<Boolean>> = _aboutEvent

    private var _trackingEvent = MutableLiveData<Event<Boolean>>()
    val trackingEvent: LiveData<Event<Boolean>> = _trackingEvent

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

    private var _clickSharedProduct = MutableLiveData<Event<String>>()
    val clickSharedProduct: LiveData<Event<String>> = _clickSharedProduct

    val categories = Repository.getCategories().asLiveData()
    val mostWantedProducts = Repository.getRecommendedProducts().asLiveData()
    val homeImages = Repository.getHomeImages().asLiveData()

    val laptopCategory = Repository.getProductsByCategoryId(CategoriesId.LAPTOP,
        Constants.PAGE_NUMBER_ZERO, Constants.SORT_BY_CREATED_DATE).asLiveData()

    val headsetsCategory = Repository.getProductsByCategoryId(CategoriesId.HEADSETS,
        Constants.PAGE_NUMBER_ZERO, Constants.SORT_BY_CREATED_DATE).asLiveData()

    val caseCategory = Repository.getProductsByCategoryId(CategoriesId.CASE,
        Constants.PAGE_NUMBER_ZERO, Constants.SORT_BY_CREATED_DATE).asLiveData()

    val padMouseCategory = Repository.getProductsByCategoryId(CategoriesId.PAD_MOUSE,
        Constants.PAGE_NUMBER_ZERO, Constants.SORT_BY_CREATED_DATE).asLiveData()

    private var _toast = MutableLiveData<Event<String>>()
    val toast: LiveData<Event<String>> = _toast


    val state=MediatorLiveData<State<Any>>().apply {
        addSource(homeImages,this@HomeViewModel::checkIfSuccess)
        addSource(categories,this@HomeViewModel::checkIfSuccess)
        addSource(mostWantedProducts,this@HomeViewModel::checkIfSuccess)
        addSource(laptopCategory,this@HomeViewModel::checkIfSuccess)
        addSource(headsetsCategory,this@HomeViewModel::checkIfSuccess)
        addSource(caseCategory,this@HomeViewModel::checkIfSuccess)
        addSource(padMouseCategory,this@HomeViewModel::checkIfSuccess)
    }


    private fun <T>checkIfSuccess(currentState:State<T>){
        if(currentState is State.Success){
            state.postValue(currentState as State<Any>)
        }
        takeIf { currentState is State.Error }?.let {
            if(ifAllStateError()){
                state.postValue(State.Error(Constants.NO_INTERNET))
            }
        }
    }

    private fun ifAllStateError() =
        checkState(
            homeImages.value,
            categories.value,
            mostWantedProducts.value,
            laptopCategory.value,
            caseCategory.value,
            headsetsCategory.value,
            padMouseCategory.value
        )


    private fun <T>checkState(vararg state:State<T>?) = state.all { it is State.Error }


    private fun addItem(product: Product) {
        viewModelScope.launch {
            if (!isItemExists(product)!!)
                checkIfItemSold(product)

            else
                getPiecesNumber(product)
        }
    }

    private suspend fun checkIfItemSold(product: Product) {
        if (product.sold == true) {
            _toast.postValue(Event("Sorry Product Is Sold Out \uD83D\uDE22"))
        }

        else {
            setItem(product)?.let { Repository.insertCartItem(it) }
            _toast.postValue(Event("Added 1 Piece To Cart"))
        }

    }


    private suspend fun getPiecesNumber(product: Product) {
        viewModelScope.launch {
            Repository.getCartItemById(product.id)?.let {
                updateItem(product, it.pieces.plus(1))
                _toast.postValue(Event("Added ${it.pieces.plus(1)} Piece To Cart"))
            }
        }
    }


    private suspend fun updateItem(product: Product, piecesNumber: Int) {
        viewModelScope.launch {
            Repository.getCartItemById(product.id)
                ?.let {
                    Repository.updateCartItem(
                        it.id,
                        piecesNumber,
                        it.price.times(piecesNumber)
                    )
                }
        }
    }


    private suspend fun isItemExists(product: Product?) =
        product?.let { Repository.checkCartItemExists(it.id) }

    fun setItem(product: Product?) =
        product?.toCartItemEntity(1)



    fun onClickCart(){
        _cartEvent.postValue(Event(true))
    }

    fun onClickAbout(){
        _aboutEvent.postValue(Event(true))
    }

    fun onClickTracking(){
        _trackingEvent.postValue(Event(true))
    }

    override fun onclickSearch() {
        _searchEvent.postValue(Event(true))
    }

    override fun onClickProduct(productId: String) {
        _onclickProductEvent.postValue(Event(productId))
    }

    override fun onclickAddToCart(productId: Product) {
        addItem(productId)
    }

    override fun onClickShare(productId: String) {
        _clickSharedProduct.postValue(Event(productId))
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

    override fun onClickSliderItem(position: Int) {
        homeImages.value?.toData()?.let { images ->
            _onclickProductEvent.postValue(Event(images[position].productId))
        }
    }



}
