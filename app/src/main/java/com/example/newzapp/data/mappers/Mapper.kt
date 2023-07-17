package com.example.newzapp.data.mappers

import com.example.newzapp.data.local.entities.NewsResponseEntity
import com.example.newzapp.data.local.entities.NewsSourcesEntity
import com.example.newzapp.data.local.entities.TopHeadlinesEntity
import com.example.newzapp.data.model.NewsResponseDTO
import com.example.newzapp.data.model.NewsSourcesDTO
import com.example.newzapp.data.model.TopHeadlinesDTO
import com.example.newzapp.data.model.toEntity

object Mapper {
  inline fun <reified T, reified A> A.networkResponseToRoomEntity(): T {
    return when {
      T::class.java == NewsResponseEntity::class.java && A::class.java == NewsResponseDTO::class.java -> (this as NewsResponseDTO).toEntity() as T
      T::class.java == NewsSourcesEntity::class.java && A::class.java == NewsSourcesDTO::class.java -> (this as NewsSourcesDTO).toEntity() as T
      T::class.java == TopHeadlinesEntity::class.java && A::class.java == TopHeadlinesDTO::class.java -> (this as TopHeadlinesDTO).toEntity() as T
      else -> throw Exception("Unsupported types found")
    }
  }
}