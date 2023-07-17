package com.example.newzapp.data.model


import com.example.newzapp.data.local.entities.NewsResponseEntity
import com.google.gson.annotations.SerializedName

data class NewsResponseDTO(
  @SerializedName("articles")
  val articles: List<Article?>?,
  @SerializedName("status")
  val status: String?,
  @SerializedName("totalResults")
  val totalResults: Int?
) {
  data class Article(
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("source")
    val source: Source?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?
  ) {
    data class Source(
      @SerializedName("id")
      val id: String?,
      @SerializedName("name")
      val name: String?
    )
  }
}

fun NewsResponseDTO.toEntity() = NewsResponseEntity(0, articles?.map {
  it?.toEntity()
}, status, totalResults)

fun NewsResponseDTO.Article.toEntity(): NewsResponseEntity.Article = NewsResponseEntity.Article(
  author,
  content,
  description,
  publishedAt,
  source?.toEntity(),
  title,
  url,
  urlToImage
)

fun NewsResponseDTO.Article.Source.toEntity() = NewsResponseEntity.Article.Source(id, name)