package com.example.newzapp.data.repository

import com.example.newzapp.data.local.entities.NewsResponseEntity
import com.example.newzapp.data.local.entities.NewsSourcesEntity
import com.example.newzapp.data.local.entities.TopHeadlinesEntity
import com.example.newzapp.data.model.NewsResponseDTO
import com.example.newzapp.data.model.NewsSourcesDTO
import com.example.newzapp.data.model.TopHeadlinesDTO
import com.example.newzapp.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
  suspend fun getAllNewsByQuery(query: String): Flow<NetworkResult<NewsResponseEntity>>
  suspend fun getNewsSources(): Flow<NetworkResult<NewsSourcesEntity>>
  suspend fun getTopHeadlines(country: String): Flow<NetworkResult<TopHeadlinesEntity>>
}