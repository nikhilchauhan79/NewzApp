package com.example.newzapp.data.repository

import com.example.newzapp.data.model.NewsResponseDTO
import com.example.newzapp.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
  suspend fun getAllNewsByQuery(query: String): Flow<NetworkResult<NewsResponseDTO>>
}