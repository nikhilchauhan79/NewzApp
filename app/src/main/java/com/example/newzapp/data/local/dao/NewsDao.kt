package com.example.newzapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newzapp.data.local.entities.NewsResponseEntity
import com.example.newzapp.data.local.entities.NewsSourcesEntity
import com.example.newzapp.data.local.entities.TopHeadlinesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
  @Insert
  fun insertAllNews(newsResponseEntity: NewsResponseEntity)

  @Insert
  fun insertNewsSources(newsSourcesEntity: NewsSourcesEntity)

  @Insert
  fun insertTopHeadlines(topHeadlinesEntity: TopHeadlinesEntity)

  @Query("SELECT * FROM news")
  fun getAllNews(): Flow<NewsResponseEntity>

  @Query("SELECT * FROM sources")
  fun getNewsSources(): Flow<NewsSourcesEntity>

  @Query("SELECT * FROM top_headlines")
  fun getTopHeadlines(): Flow<TopHeadlinesEntity>

}