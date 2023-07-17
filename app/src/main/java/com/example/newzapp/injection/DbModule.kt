package com.example.newzapp.injection

import android.content.Context
import com.example.newzapp.data.local.NewsDatabase
import com.example.newzapp.data.local.dao.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
  @Provides
  @Singleton
  fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase =
    NewsDatabase.getDatabase(context = context)

  @Provides
  @Singleton
  fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao = newsDatabase.newsDao()
}