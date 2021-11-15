package com.lemon.team.electronics.model.repository

import com.lemon.team.electronics.model.data.Product
import com.lemon.team.electronics.model.data.database.ProductsDatabase

object DatabaseRepository {

    private val dao = ProductsDatabase.getInstanceWithContext().productsDoa()

    fun insertProduct(item: Product) = dao.insert(item)

    fun getAllProducts() = dao.getAllItems()

}