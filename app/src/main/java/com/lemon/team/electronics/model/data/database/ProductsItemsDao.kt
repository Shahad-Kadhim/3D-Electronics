package com.lemon.team.electronics.model.data.database

import androidx.room.*
import com.lemon.team.electronics.model.data.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsItemsDao {

    @Insert
    suspend fun insert(Item: Item?)

    @Delete
    fun delete(Item: Item)

    @Update
    fun update(Item: Item)

    @Query("SELECT * FROM PRODUCT_TABLE WHERE productType = 1 ORDER BY id DESC ")
    fun getAllCartItems(): Flow<List<Item>>

}