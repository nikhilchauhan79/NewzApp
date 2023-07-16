package com.example.newzapp.data.remote

import com.example.newzapp.constants.AppConstants
import com.example.newzapp.data.model.NewsResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApiService {
  @GET("everything")
  suspend fun getNewsArticles(
    @Query("q") query: String,
    @Query("apiKey") apiKey: String = AppConstants.API_KEY
  ): Response<NewsResponseDTO>
}