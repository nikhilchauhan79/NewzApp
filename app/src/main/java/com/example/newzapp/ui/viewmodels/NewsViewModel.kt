package com.example.newzapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newzapp.data.model.NewsResponseDTO
import com.example.newzapp.data.remote.NetworkResult
import com.example.newzapp.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
  private val newsRepository: NewsRepository
) : ViewModel() {
  private val _allNewsByQueryResponse: MutableStateFlow<NetworkResult<NewsResponseDTO>?> =
    MutableStateFlow(null)

  val allNewsByQueryResponse: StateFlow<NetworkResult<NewsResponseDTO>?> =
    _allNewsByQueryResponse.asStateFlow()

  init {
    getAllNewsByQuery("crypto")
  }

  fun getAllNewsByQuery(queryTerm: String) {
    viewModelScope.launch {
      _allNewsByQueryResponse.emit(NetworkResult.Loading())
      newsRepository.getAllNewsByQuery(queryTerm).collect { result ->
        _allNewsByQueryResponse.emit(result)
      }
    }
  }
}