package com.codewithharsh.newsexpress.data.remote

import com.codewithharsh.newsexpress.core.Constants
import com.codewithharsh.newsexpress.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): NewsResponse
}