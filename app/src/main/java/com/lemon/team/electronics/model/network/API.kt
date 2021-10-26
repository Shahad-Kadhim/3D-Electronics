package com.lemon.team.electronics.model.network

import com.lemon.team.electronics.util.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {

    private val client = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val apiService = retrofit.create(ProductService::class.java)

}