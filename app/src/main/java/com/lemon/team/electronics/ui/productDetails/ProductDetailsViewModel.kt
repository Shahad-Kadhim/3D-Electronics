package com.lemon.team.electronics.ui.productDetails

import androidx.lifecycle.*
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.Product
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Constants
import com.lemon.team.electronics.util.Event
import com.lemon.team.electronics.util.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ProductDetailsViewModel : BaseViewModel(),ImageInteractionListener {

    private var _detailsProduct = MutableLiveData<State<Product?>>()
    val detailsProduct :LiveData<State<Product?>> =_detailsProduct

    val images =Transformations.map(detailsProduct){
        it.toData()?.images?.map { it.productImageLocation }
    }
    val mainImage= MediatorLiveData<String>().apply {
        addSource(detailsProduct){
            if(it is State.Success){
                it.data?.mainImage?.let { url ->
                    updateMainImage(url)
                }
            }
        }
    }

    private var _onclickBack =MutableLiveData<Event<Boolean>>()
    val onclickBack :LiveData<Event<Boolean>> = _onclickBack

    private var _onclickShare =MutableLiveData<Event<String>>()
    val onclickShare :LiveData<Event<String>> = _onclickShare

    private var _onclickWish =MutableLiveData<Event<String>>()
    val onclickWish :LiveData<Event<String>> = _onclickWish

    private var _onclickAddToCart =MutableLiveData<Event<String>>()
    val onclickAddToCart :LiveData<Event<String>> = _onclickAddToCart

    private var _onClickMainImage =MutableLiveData<Event<String>>()
    val onclickMainImage :LiveData<Event<String>> = _onClickMainImage


    private fun updateMainImage(url:String){
        mainImage.postValue(url)
    }

    fun getDetailsProduct(productId: String) {

        collectResponse(
            Repository.getProductById(productId)){
            _detailsProduct.postValue(it)
        }

    }

    fun onclickBack(){
        _onclickBack.postValue(Event(true))
    }

    fun onclickWish(productId: String){
        _onclickWish.postValue(Event(productId))
    }

    fun onclickAddToCart(productId: String){
        _onclickAddToCart.postValue(Event(productId))
    }

    fun onclickShare(link:String){
        _onclickShare.postValue(Event(link))
    }

    fun onClickMainImage(){
        mainImage.value?.let { url ->
            _onClickMainImage.postValue(Event(url))
        }

    }
    override fun onclickImage(url: String) {
        mainImage.postValue(url)
    }


}
