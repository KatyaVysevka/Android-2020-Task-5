package com.example.task5.api

import com.example.task5.data.CatPhoto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CatApi {

    companion object {
        const val BASE_URL = "https://api.thecatapi.com/v1/"
        const val ACCESS_KEY = "a4723fae-e3de-4a74-8dd5-2701dcf03e3f"
    }

    @Headers("Content-Type: application/json", "x-api-key: $ACCESS_KEY")
    @GET("images/search")
    suspend fun catPhotos(
        @Query("order") order: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<CatPhoto>
}
