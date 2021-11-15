package com.lemon.team.electronics.model.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lemon.team.electronics.model.data.ProductItem



@Database(entities = [ProductItem::class], version = 1)
abstract class ProductsItemsDatabase: RoomDatabase() {

    abstract fun productsDoa(): ProductsItemsDao

    companion object{

        private const val DATABASE_NAME = "ProductsDataBase"

        private var instance: ProductsItemsDatabase? = null

        fun getInstance(context: Context): ProductsItemsDatabase{
            return instance ?: synchronized(this){
                buildDatabase(context)
                .also{ dataBase ->
                    instance = dataBase
                }
            }
        }

        fun getInstanceWithContext() = instance!!


        private fun buildDatabase(context: Context): ProductsItemsDatabase{
            return Room.databaseBuilder(
                context,
                ProductsItemsDatabase::class.java,
                DATABASE_NAME)
                .build()
        }

    }

}