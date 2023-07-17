package com.example.newzapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newzapp.data.local.dao.NewsDao
import com.example.newzapp.data.local.entities.NewsResponseEntity
import com.example.newzapp.data.local.entities.NewsSourcesEntity
import com.example.newzapp.data.local.entities.TopHeadlinesEntity

@Database(
  entities = [NewsResponseEntity::class, TopHeadlinesEntity::class, NewsSourcesEntity::class],
  version = 1
)
@TypeConverters(Convertors::class)
abstract class NewsDatabase : RoomDatabase() {
  abstract fun newsDao(): NewsDao

  companion object {
    @Volatile
    private var instance: NewsDatabase? = null

    fun getDatabase(context: Context): NewsDatabase = instance ?: synchronized(this) {
      instance ?: buildDatabase(
        context
      ).also {
        instance = it
      }
    }

    private fun buildDatabase(context: Context) =
      Room.databaseBuilder(context, NewsDatabase::class.java, "news_database").build()
  }
}