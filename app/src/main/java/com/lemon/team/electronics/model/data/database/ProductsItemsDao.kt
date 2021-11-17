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

    @Query("SELECT * FROM PRODUCT_TABLE ORDER BY id DESC ")
    fun getAllCartItems(): Flow<List<ProductItem>>

    @Query("SELECT * FROM PRODUCT_TABLE WHERE itemId = :id ")
    fun getItemByID(id: String): Flow<ProductItem?>

    @Query("SELECT EXISTS (SELECT * FROM PRODUCT_TABLE WHERE itemId = :itemId)")
    suspend fun isItemExists(itemId: String): Boolean

    @Query("SELECT SUM(price) as total FROM PRODUCT_TABLE ")
    fun getTotalPrice(): Flow<Double>

    @Query("SELECT SUM(oldPrice) as total FROM PRODUCT_TABLE ")
    fun getOldTotalPrice(): Flow<Double>

    @Query("SELECT COUNT(*) FROM PRODUCT_TABLE")
    fun getPiecesNumber(): Flow<Int>

    @Query("DELETE FROM PRODUCT_TABLE WHERE itemId = :id")
    suspend fun deleteById(id: String?)

}