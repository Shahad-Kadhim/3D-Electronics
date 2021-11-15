package com.lemon.team.electronics.model.data.database

import androidx.room.*
import com.lemon.team.electronics.model.data.ProductItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsItemsDao {

    @Insert
    suspend fun insert(ProductItem: ProductItem)

    @Delete
    fun delete(ProductItem: ProductItem)

    @Update
    fun update(ProductItem: ProductItem)

    @Query("SELECT * FROM PRODUCT_TABLE WHERE productType = 1 ORDER BY id DESC ")
    fun getAllCartItems(): Flow<List<ProductItem>>

}