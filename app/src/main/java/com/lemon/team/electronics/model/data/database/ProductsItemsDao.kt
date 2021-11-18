package com.lemon.team.electronics.model.data.database

import androidx.room.*
import com.lemon.team.electronics.model.data.CartItem
import com.lemon.team.electronics.model.data.WishItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsItemsDao {

    @Insert
    suspend fun insert(CartItem: CartItem)

    @Delete
    fun delete(CartItem: CartItem)

    @Query("UPDATE CART_TABLE SET pieces= :pieces, price= :price WHERE id = :itemId")
    suspend fun updateCartItem(itemId: String, pieces: Int, price: Double)

    @Query("SELECT * FROM CART_TABLE ORDER BY id DESC ")
    fun getAllCartItems(): Flow<List<CartItem>>

    @Query("SELECT * FROM CART_TABLE WHERE id = :id ")
    suspend fun getItemByID(id: String): CartItem?

    @Query("SELECT EXISTS (SELECT * FROM CART_TABLE WHERE id = :itemId)")
    suspend fun isItemExists(itemId: String): Boolean

    @Query("SELECT SUM(price) as total FROM CART_TABLE ")
    fun getTotalPrice(): Flow<Double>

    @Query("SELECT SUM(oldPrice) as total FROM CART_TABLE ")
    fun getOldTotalPrice(): Flow<Double>

    @Query("SELECT COUNT(*) FROM CART_TABLE")
    fun getPiecesNumber(): Flow<Int>

    @Query("DELETE FROM CART_TABLE WHERE id = :id")
    suspend fun deleteItemById(id: String?)



    @Query("SELECT * FROM WISH_TABLE ORDER BY id DESC ")
    fun getAllWishItems(): Flow<List<WishItem>>

}