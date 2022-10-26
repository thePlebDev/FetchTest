package com.elliottsoftware.fetchtest.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//TODO: THIS WILL BE REPLACE WITH A HILT INJECTION MODULE
object RetrofitInstance {
    val api:FetchApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FetchApi::class.java)
    }
}