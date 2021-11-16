package com.lemon.team.electronics.model.repository

import android.util.Log
import androidx.lifecycle.asLiveData
import com.lemon.team.electronics.model.data.ProductItem
import com.lemon.team.electronics.model.data.database.ProductsItemsDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

object DatabaseRepository {

    private val dao = ProductsItemsDatabase.getInstanceWithContext().productsDoa()

    suspend fun insertProduct(ProductItem: ProductItem) = dao.insert(ProductItem)

    suspend fun checkExists(itemId: String) = dao.exists(itemId)

    suspend fun updateCartItem(itemId: String, pieces: Int, price: Double) =
        dao.updateCartItem(itemId, pieces, price)

    fun getAllProducts() = dao.getAllCartItems()


    fun getItemById(id: String) =
        dao.getItemByID(id)


    fun getTotalPrice() = dao.getTotalPrice()

}