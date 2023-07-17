package com.example.newzapp.data.local

import androidx.room.TypeConverter
import com.example.newzapp.data.local.entities.NewsResponseEntity
import com.example.newzapp.data.local.entities.NewsSourcesEntity
import com.example.newzapp.data.local.entities.TopHeadlinesEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Convertors {
  @TypeConverter
  fun newsArticleToJson(value: List<NewsResponseEntity.Article?>?): String {
    val type = object : TypeToken<List<NewsResponseEntity.Article?>>() {}.type
    return Gson().toJson(value, type)
  }

  @TypeConverter
  fun newsSourceToJson(value: NewsResponseEntity.Article.Source?): String {
    val type = object : TypeToken<NewsResponseEntity.Article.Source?>() {}.type
    return Gson().toJson(value, type)
  }

  @TypeConverter
  fun newsSourcesToJson(value: List<NewsSourcesEntity.Source?>?): String {
    val type = object : TypeToken<List<NewsSourcesEntity.Source>>() {}.type
    return Gson().toJson(value, type)
  }

  @TypeConverter
  fun topHeadlinesArticleToJson(value: List<TopHeadlinesEntity.Article?>?): String {
    val type = object : TypeToken<List<TopHeadlinesEntity.Article>>() {}.type
    return Gson().toJson(value, type)
  }

  @TypeConverter
  fun topHeadlinesSourceToJson(value: TopHeadlinesEntity.Article.Source?): String {
    val type = object : TypeToken<TopHeadlinesEntity.Article.Source>() {}.type
    return Gson().toJson(value)
  }


  @TypeConverter
  fun toNewsArticle(value: String): List<NewsResponseEntity.Article?>? {
    val type = object : TypeToken<List<NewsResponseEntity.Article>>() {}.type
    return Gson().fromJson(value, type)
  }

  @TypeConverter
  fun toNewsSource(value: String): NewsResponseEntity.Article.Source? {
    val type = object : TypeToken<NewsResponseEntity.Article.Source>() {}.type
    return Gson().fromJson(value, type)
  }

  @TypeConverter
  fun toNewsSources(value: String): List<NewsSourcesEntity.Source?>? {
    val type = object : TypeToken<List<NewsSourcesEntity.Source>>() {}.type
    return Gson().fromJson(value, type)
  }

  @TypeConverter
  fun toTopHeadlinesArticle(value: String): List<TopHeadlinesEntity.Article?>? {
    val type = object : TypeToken<List<TopHeadlinesEntity.Article>>() {}.type
    return Gson().fromJson(value, type)
  }

  @TypeConverter
  fun toTopHeadlinesSource(value: String): TopHeadlinesEntity.Article.Source? {
    val type = object : TypeToken<TopHeadlinesEntity.Article.Source>() {}.type
    return Gson().fromJson(value, type)
  }
}