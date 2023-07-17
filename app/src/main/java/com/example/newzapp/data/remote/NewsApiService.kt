package com.example.newzapp.data.remote

import com.example.newzapp.constants.AppConstants
import com.example.newzapp.data.model.NewsResponseDTO
import com.example.newzapp.data.model.NewsSourcesDTO
import com.example.newzapp.data.model.TopHeadlinesDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApiService {
  @GET("everything")
  suspend fun getNewsArticles(
    @Query("q") query: String,
    @Query("apiKey") apiKey: String = AppConstants.API_KEY
  ): Response<NewsResponseDTO>

  @GET("top-headlines/sources")
  suspend fun getNewsSources(
    @Query("apiKey") apiKey: String = AppConstants.API_KEY
  ): Response<NewsSourcesDTO>

  @GET("top-headlines")
  suspend fun getTopHeadlines(
    @Query("country") country: String,
    @Query("apiKey") apiKey: String = AppConstants.API_KEY
  ): Response<TopHeadlinesDTO>

}