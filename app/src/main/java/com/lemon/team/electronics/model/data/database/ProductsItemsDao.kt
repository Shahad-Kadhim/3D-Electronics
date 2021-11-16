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

    @Query("UPDATE PRODUCT_TABLE SET pieces= :pieces, price= :price WHERE itemId = :itemId")
    suspend fun updateCartItem(itemId: String, pieces: Int, price: Double)

    @Query("SELECT * FROM PRODUCT_TABLE WHERE productType = 1 ORDER BY id DESC ")
    fun getAllCartItems(): Flow<List<ProductItem>>

    @Query("SELECT * FROM PRODUCT_TABLE WHERE itemId = :id ")
    fun getItemByID(id: String):Flow<ProductItem>

    @Query("SELECT EXISTS (SELECT * FROM PRODUCT_TABLE WHERE itemId = :itemId)")
    suspend fun exists(itemId: String): Boolean

    @Query("SELECT SUM(price) as total FROM PRODUCT_TABLE ")
    fun getTotalPrice(): Flow<Double>
}