package com.example.newzapp.data.local.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sources")
data class NewsSourcesEntity(
  @PrimaryKey(autoGenerate = true) val id: Int,
  @ColumnInfo(name = "sources")
  val sources: List<Source?>?,
  @ColumnInfo(name = "status")
  val status: String?
) {
  data class Source(
    @ColumnInfo(name = "category")
    val category: String?,
    @ColumnInfo(name = "country")
    val country: String?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "id")
    val id: String?,
    @ColumnInfo(name = "language")
    val language: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "url")
    val url: String?
  )
}