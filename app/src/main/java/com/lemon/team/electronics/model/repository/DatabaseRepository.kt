package com.lemon.team.electronics.model.repository

import com.lemon.team.electronics.model.data.ProductItem
import com.lemon.team.electronics.model.data.database.ProductsItemsDatabase

object DatabaseRepository {

    private val dao = ProductsItemsDatabase.getInstanceWithContext().productsDoa()

    suspend fun insertProduct(ProductItem: ProductItem) = dao.insert(ProductItem)

    suspend fun checkExists(itemId: String) = dao.exists(itemId)

    suspend fun updateCartItem(itemId: String, pieces: Int, price: Double) =
        dao.updateCartItem(itemId, pieces, price)

    fun getAllProducts() = dao.getAllCartItems()

}