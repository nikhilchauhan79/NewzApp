package com.example.newzapp.data.repository

import com.example.newzapp.data.local.dao.NewsDao
import com.example.newzapp.data.local.entities.NewsResponseEntity
import com.example.newzapp.data.local.entities.NewsSourcesEntity
import com.example.newzapp.data.local.entities.TopHeadlinesEntity
import com.example.newzapp.data.model.NewsResponseDTO
import com.example.newzapp.data.model.NewsSourcesDTO
import com.example.newzapp.data.model.TopHeadlinesDTO
import com.example.newzapp.data.model.toEntity
import com.example.newzapp.data.remote.NetworkResult
import com.example.newzapp.data.remote.RemoteDataSource
import com.example.newzapp.data.remote.helper.BaseApiResponse
import com.example.newzapp.data.remote.helper.performGetOperation
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class NewsRepositoryImpl @Inject constructor(
  private val remoteDataSource: RemoteDataSource, private val newsDao: NewsDao
) : NewsRepository, BaseApiResponse() {
  override suspend fun getAllNewsByQuery(query: String): Flow<NetworkResult<NewsResponseEntity>> =
    performGetOperation<NewsResponseEntity, NewsResponseDTO>(databaseQuery = {
      newsDao.getAllNews()
    }, networkCall = {
      safeApiCall {
        remoteDataSource.getAllNewsByQuery(query)
      }
    }, saveResultCall = {
      newsDao.insertAllNews(it.toEntity())
    })


  override suspend fun getNewsSources(): Flow<NetworkResult<NewsSourcesEntity>> =
    performGetOperation<NewsSourcesEntity, NewsSourcesDTO>(databaseQuery = {
      newsDao.getNewsSources()
    }, networkCall = {
      safeApiCall {
        remoteDataSource.getNewsSources()
      }
    }, saveResultCall = {
      newsDao.insertNewsSources(it.toEntity())
    })

  override suspend fun getTopHeadlines(country: String): Flow<NetworkResult<TopHeadlinesEntity>> =
    performGetOperation<TopHeadlinesEntity, TopHeadlinesDTO>(databaseQuery = {
      newsDao.getTopHeadlines()
    }, networkCall = {
      safeApiCall {
        remoteDataSource.getTopHeadlines(country)
      }
    }, saveResultCall = {
      newsDao.insertTopHeadlines(it.toEntity())
    })
}