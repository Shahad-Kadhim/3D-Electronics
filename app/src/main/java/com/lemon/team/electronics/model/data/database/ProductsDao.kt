package com.lemon.team.electronics.model.data.database

import androidx.room.*
import com.lemon.team.electronics.model.data.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Insert
    fun insert(item: Product)

    @Delete
    fun delete(item: Product)

    @Update
    fun update(item: Product)

    @Query("SELECT * FROM PRODUCT_TABLE ")
    fun getAllItems(): Flow<List<Product>>

}