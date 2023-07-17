package com.example.newzapp.data.local.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "top_headlines")
data class TopHeadlinesEntity(
  @PrimaryKey(autoGenerate = true) val id: Int,
  @ColumnInfo(name = "articles")
  val articles: List<Article?>?,
  @ColumnInfo(name = "status")
  val status: String?,
  @ColumnInfo(name = "totalResults")
  val totalResults: Int?
) {
  data class Article(
    @ColumnInfo(name = "author")
    val author: String?,
    @ColumnInfo(name = "content")
    val content: String?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String?,
    @ColumnInfo(name = "source")
    val source: Source?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "url")
    val url: String?,
    @ColumnInfo(name = "urlToImage")
    val urlToImage: String?
  ) {
    data class Source(
      @ColumnInfo(name = "id")
      val id: String?,
      @ColumnInfo(name = "name")
      val name: String?
    )
  }
}

fun TopHeadlinesEntity.toAllNews() = NewsResponseEntity(0, articles?.map {
  it?.toAllNews()
}, status, totalResults)

fun TopHeadlinesEntity.Article.toAllNews() = NewsResponseEntity.Article(
  author, content, description, publishedAt,
  source?.toAllNews(), title, url, urlToImage
)

fun TopHeadlinesEntity.Article.Source.toAllNews() = NewsResponseEntity.Article.Source(
  id, name
)