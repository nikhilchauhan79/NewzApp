package com.example.newzapp.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val newsApiService: NewsApiService) {
  suspend fun getAllNewsByQuery(query: String) = newsApiService.getNewsArticles(query)

  suspend fun getNewsSources() = newsApiService.getNewsSources()

  suspend fun getTopHeadlines(country: String) = newsApiService.getTopHeadlines(country)

}