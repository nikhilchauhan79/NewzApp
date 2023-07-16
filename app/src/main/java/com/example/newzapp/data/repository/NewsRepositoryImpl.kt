package com.example.newzapp.data.repository

import com.example.newzapp.data.model.NewsResponseDTO
import com.example.newzapp.data.remote.NetworkResult
import com.example.newzapp.data.remote.NewsApiService
import com.example.newzapp.data.remote.helper.BaseApiResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class NewsRepositoryImpl @Inject constructor(
  private val newsApiService: NewsApiService
) : NewsRepository, BaseApiResponse() {
  override suspend fun getAllNewsByQuery(query: String): Flow<NetworkResult<NewsResponseDTO>> =
    flow {
      val result = safeApiCall {
        newsApiService.getNewsArticles(query)
      }
      emit(result)
    }.flowOn(Dispatchers.IO)
}