package com.lemon.team.electronics.model.repository

import com.lemon.team.electronics.model.data.Item
import com.lemon.team.electronics.model.data.database.ProductsItemsDatabase

object DatabaseRepository {

    private val dao = ProductsItemsDatabase.getInstanceWithContext().productsDoa()

    suspend fun insertProduct(Item: Item?) = dao.insert(Item)

    fun getAllProducts() = dao.getAllCartItems()

}