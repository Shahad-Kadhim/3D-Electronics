package com.lemon.team.electronics.ui.productDetails

import androidx.lifecycle.*
import com.lemon.team.electronics.data.ElectronicRepository
import com.lemon.team.electronics.data.remote.response.Product
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repository: ElectronicRepository
): BaseViewModel() ,ImageInteractionListener {

    private var _detailsProduct = MutableLiveData<State<Product?>>()
    val detailsProduct :LiveData<State<Product?>> =_detailsProduct

    private var _onclickBack =MutableLiveData<Event<Boolean>>()
    val onclickBack :LiveData<Event<Boolean>> = _onclickBack

    private var _onclickWish =MutableLiveData<Event<String>>()
    val onclickWish :LiveData<Event<String>> = _onclickWish

    private var _toast =MutableLiveData<Event<String>>()
    val toast :LiveData<Event<String>> = _toast

    private var _onClickMainImage =MutableLiveData<Event<String>>()
    val onclickMainImage :LiveData<Event<String>> = _onClickMainImage

    private var _clickSharedProduct = MutableLiveData<Event<String>>()
    val clickSharedProduct: LiveData<Event<String>> = _clickSharedProduct


    val images =Transformations.map(detailsProduct){ state ->
        state.toData()?.images?.map { imageResponse ->
            imageResponse.productImageLocation
        }
    }

    val mainImage= MediatorLiveData<String>().apply {
        addSource(detailsProduct){ state ->
            if(state is State.Success){
                state.data?.mainImage?.let { url ->
                    updateMainImage(url)
                }
            }
        }
    }

    private fun updateMainImage(url:String){
        mainImage.postValue(url)
    }


    override fun onclickImage(url: String) {
        mainImage.postValue(url)
    }

    fun getDetailsProduct(productId: String) {
        collectResponse(repository.getProductById(productId)) { state ->
            _detailsProduct.postValue(state)
        }
        getDetailsProductFromDatabase(productId)
    }

    var piecesNumber = MutableLiveData(1)
    private fun getDetailsProductFromDatabase(productId: String){
        viewModelScope.launch {
            if (repository.checkCartItemExists(productId))
                repository.getCartItemById(productId)?.let {
                    piecesNumber.postValue(it.pieces)
                }
            checkIfItemInWishTable(productId)
        }
    }

    private suspend fun checkIfItemInWishTable(productId: String) {
        if (repository.checkWishItemExists(productId))
            checkHeart.postValue(true)
    }

    fun onclickAddToCart(){
        addOrUpdateItem(detailsProduct.value?.toData())
    }


    private fun addOrUpdateItem(item: Product?) {
        viewModelScope.launch {
            if (!isCartItemExists()!!)
                setCartItem(item)?.let { repository.insertCartItem(it) }
            else
                updateItem(item)
        }
        setPiecesNumberInToast()
    }

    private fun setPiecesNumberInToast(){
        _toast.postValue(Event("Added ${piecesNumber.value} Piece To Cart"))
    }

    private fun setCartItem(product: Product?) =
        piecesNumber.value?.let { product?.toCartItemEntity(it) }

    private suspend fun isCartItemExists() =
        detailsProduct.value?.toData()?.let { repository.checkCartItemExists(it.id) }


    private suspend fun  updateItem(product: Product?) {
        product?.price.let {
            repository.updateCartItem(
                product!!.id,
                piecesNumber.value!!,
                it!!.times(piecesNumber.value!!)
            )
        }
    }


    var checkHeart = MutableLiveData<Boolean>()
    fun onclickWish(){
        viewModelScope.launch {
            if (checkHeart.value == true)
                addToWishList(detailsProduct.value?.toData())
            else
                repository.deleteWishItemById(detailsProduct.value?.toData()?.id)
        }
    }

    private fun addToWishList(product: Product?) {
        viewModelScope.launch {
            if (!isWishItemExists()!!)
                setWishItem(product)?.let { repository.insertWishItem(it) }
    }}

    private fun setWishItem(product: Product?) =
        product?.toWishItemEntity()

    private suspend fun isWishItemExists() =
        detailsProduct.value?.toData()?.let { repository.checkWishItemExists(it.id) }



    fun onclickBack(){
        _onclickBack.postValue(Event(true))
    }

    fun onclickShare(productId: String){
        _clickSharedProduct.postValue(Event(productId))
    }

    fun onClickMainImage(){
        mainImage.value?.let { url ->
            _onClickMainImage.postValue(Event(url))
        }
    }

}
