package com.lemon.team.electronics.data.local.database

import androidx.room.Database
import androidx.room.*
import com.lemon.team.electronics.data.local.CartItem
import com.lemon.team.electronics.data.local.WishItem


@Database(entities = [CartItem::class, WishItem::class], version = 1)
abstract class ProductsItemsDatabase: RoomDatabase() {

    abstract fun productsDao(): ProductsItemsDao

    companion object {

        const val DATABASE_NAME = "ProductsDataBase"

    }
}