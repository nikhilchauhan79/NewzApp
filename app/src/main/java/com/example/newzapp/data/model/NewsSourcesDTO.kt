package com.example.newzapp.data.model


import com.example.newzapp.data.local.entities.NewsSourcesEntity
import com.google.gson.annotations.SerializedName

data class NewsSourcesDTO(
  @SerializedName("sources")
  val sources: List<Source?>?,
  @SerializedName("status")
  val status: String?
) {
  data class Source(
    @SerializedName("category")
    val category: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
  )
}

fun NewsSourcesDTO.toEntity() = NewsSourcesEntity(0, sources?.map {
  it?.toEntity()
}, status)

fun NewsSourcesDTO.Source.toEntity() =
  NewsSourcesEntity.Source(category, country, description, id, language, name, url)