package com.lemon.team.electronics.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.lemon.team.electronics.data.local.database.ProductsItemsDao
import com.lemon.team.electronics.data.local.database.ProductsItemsDatabase
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.*

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ProductsItemsDatabase =
        Room.databaseBuilder(
            context,
            ProductsItemsDatabase::class.java,
            ProductsItemsDatabase.DATABASE_NAME
        ).build()


    @Singleton
    @Provides
    fun provideDao(database: ProductsItemsDatabase): ProductsItemsDao =
        database.productsDao()

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()
}