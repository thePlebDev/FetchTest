package com.elliottsoftware.fetchtest.data.remote

import com.elliottsoftware.fetchtest.data.models.FetchItem
import retrofit2.Response
import retrofit2.http.GET

interface FetchApi {

    @GET("/hiring.json")
    suspend fun getPosts(): Response<List<FetchItem>>
}