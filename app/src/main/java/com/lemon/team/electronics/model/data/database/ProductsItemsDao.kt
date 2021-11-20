package com.lemon.team.electronics.model.data.database

import androidx.room.*
import com.lemon.team.electronics.model.data.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsItemsDao {

    @Insert
    suspend fun insertCartItem(CartItem: CartItem)

    @Insert
    suspend fun insertWishItem(WishItem: WishItem)


    @Query("SELECT * FROM CART_TABLE ORDER BY id DESC ")
    fun getAllCartItems(): Flow<List<CartItem>>

    @Query("SELECT * FROM CART_TABLE ORDER BY id DESC ")
    suspend fun getCartItems(): List<CartItem>

    @Query("SELECT * FROM WISH_TABLE ORDER BY id DESC ")
    fun getAllWishItems(): Flow<List<WishItem>>


    @Query("SELECT * FROM CART_TABLE WHERE id = :id ")
    suspend fun getICartItemByID(id: String): CartItem?

    @Query("SELECT * FROM WISH_TABLE WHERE id = :id ")
    suspend fun getIWishItemByID(id: String): WishItem?


    @Query("SELECT EXISTS (SELECT * FROM CART_TABLE WHERE id = :itemId)")
    suspend fun isCartItemExists(itemId: String): Boolean

    @Query("SELECT EXISTS (SELECT * FROM WISH_TABLE WHERE id = :itemId)")
    suspend fun isWishItemExists(itemId: String): Boolean


    @Query("DELETE FROM CART_TABLE WHERE id = :id")
    suspend fun deleteItemById(id: String?)

    @Query("DELETE FROM WISH_TABLE WHERE id = :id")
    suspend fun deleteWishItemById(id: String?)

    @Query("DELETE FROM CART_TABLE")
    suspend fun deleteCartItems()


    @Query("UPDATE CART_TABLE SET pieces= :pieces, price= :price WHERE id = :itemId")
    suspend fun updateCartItem(itemId: String, pieces: Int, price: Double)

    @Query("SELECT SUM(price) as total FROM CART_TABLE ")
    fun getTotalPrice(): Flow<Double>

    @Query("SELECT SUM(oldPrice) as total FROM CART_TABLE ")
    fun getOldTotalPrice(): Flow<Double>

    @Query("SELECT SUM(pieces) FROM CART_TABLE")
    fun getPiecesNumber(): Flow<Int>

}