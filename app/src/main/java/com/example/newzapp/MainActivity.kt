package com.example.newzapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newzapp.data.local.entities.NewsResponseEntity
import com.example.newzapp.data.remote.NetworkResult
import com.example.newzapp.ui.screens.news.components.NewsList
import com.example.newzapp.ui.theme.NewzAppTheme
import com.example.newzapp.ui.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      NewzAppTheme {
        val newsViewModel: NewsViewModel = hiltViewModel()
        val allNews = newsViewModel.allNewsByQueryResponse.collectAsStateWithLifecycle().value
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          Scaffold(topBar = {
            TopAppBar(title = {
              Text("Newz App")
            })
          }) {
            Column(
              modifier = Modifier.padding(it).fillMaxSize(),
              verticalArrangement = Arrangement.Center,
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              HandleAllNewsByQueryResponse(allNews)
            }
          }
        }
      }
    }
  }

  @Composable
  private fun HandleAllNewsByQueryResponse(
    allNews: NetworkResult<NewsResponseEntity>?,
  ) {
    when (allNews) {
      is NetworkResult.Error -> {
        Text(text = allNews.message ?: "", style = MaterialTheme.typography.headlineMedium)
      }

      is NetworkResult.Loading -> {
        CircularProgressIndicator(
          modifier = Modifier.size(80.dp),
          color = Color.Blue,
          strokeWidth = 6.dp
        )
      }

      is NetworkResult.Success -> {
        NewsList(
          newsItems = allNews.data?.articles?.filterNotNull() ?: emptyList(),
        )
      }

      null -> {
        CircularProgressIndicator()
      }
    }
  }
}