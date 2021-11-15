package com.lemon.team.electronics.model.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lemon.team.electronics.model.data.Product



@Database(entities = [Product::class], version = 1)
abstract class ProductsDatabase: RoomDatabase() {
    abstract fun productsDoa(): ProductsDao

    companion object{

        private const val DATABASE_NAME = "ProductsDataBase"

        private var instance: ProductsDatabase? = null

        fun getInstance(context: Context): ProductsDatabase{
            return instance ?: synchronized(this){
                buildDatabase(context)
                .also{ dataBase ->
                    instance = dataBase
                }
            }
        }

        fun getInstanceWithContext() = instance!!


        private fun buildDatabase(context: Context): ProductsDatabase{
            return Room.databaseBuilder(
                context,
                ProductsDatabase::class.java,
                DATABASE_NAME)
                .build()
        }

    }
}