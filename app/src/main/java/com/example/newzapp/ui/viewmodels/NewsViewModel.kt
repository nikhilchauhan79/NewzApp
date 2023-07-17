package com.example.newzapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newzapp.data.local.entities.NewsResponseEntity
import com.example.newzapp.data.local.entities.NewsSourcesEntity
import com.example.newzapp.data.local.entities.TopHeadlinesEntity
import com.example.newzapp.data.remote.NetworkResult
import com.example.newzapp.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
  private val newsRepository: NewsRepository
) : ViewModel() {
  private val _allNewsByQueryResponse: MutableStateFlow<NetworkResult<NewsResponseEntity>?> =
    MutableStateFlow(null)
  private val _allNewsSourcesResponse: MutableStateFlow<NetworkResult<NewsSourcesEntity>?> =
    MutableStateFlow(null)
 private val _topHeadlinesResponse: MutableStateFlow<NetworkResult<TopHeadlinesEntity>?> =
    MutableStateFlow(null)
  val selectedArticle: MutableStateFlow<NewsResponseEntity.Article?> = MutableStateFlow(null)

  val allNewsByQueryResponse: StateFlow<NetworkResult<NewsResponseEntity>?> =
    _allNewsByQueryResponse.asStateFlow()
  val allNewsSourcesResponse: StateFlow<NetworkResult<NewsSourcesEntity>?> =
    _allNewsSourcesResponse.asStateFlow()
 val topHeadlinesResponse: StateFlow<NetworkResult<TopHeadlinesEntity>?> =
    _topHeadlinesResponse.asStateFlow()

  init {
    getAllNewsByQuery("crypto")
    getNewsSources()
    getTopHeadlines()
  }

  fun getNewsSources() {
    viewModelScope.launch {
      _allNewsSourcesResponse.emit(NetworkResult.Loading())
      newsRepository.getNewsSources().collect{result ->
        _allNewsSourcesResponse.emit(result)
      }
    }
  }

  fun getAllNewsByQuery(queryTerm: String) {
    viewModelScope.launch {
      _allNewsByQueryResponse.emit(NetworkResult.Loading())
      newsRepository.getAllNewsByQuery(queryTerm).collect { result ->
        _allNewsByQueryResponse.emit(result)
      }
    }
  }

  fun getTopHeadlines(country: String = "in") {
    viewModelScope.launch {
      _topHeadlinesResponse.emit(NetworkResult.Loading())
      newsRepository.getTopHeadlines(country).collect { result ->
        _topHeadlinesResponse.emit(result)
      }
    }
  }
}