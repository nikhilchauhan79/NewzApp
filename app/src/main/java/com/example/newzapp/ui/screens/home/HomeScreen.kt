package com.example.newzapp.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.newzapp.data.local.entities.NewsResponseEntity
import com.example.newzapp.data.local.entities.NewsSourcesEntity
import com.example.newzapp.data.local.entities.TopHeadlinesEntity
import com.example.newzapp.data.local.entities.toAllNews
import com.example.newzapp.data.remote.NetworkResult
import com.example.newzapp.ui.screens.bottomnav.NewzBottomNavBar
import com.example.newzapp.ui.screens.bottomnav.NewzNavigation
import com.example.newzapp.ui.screens.news.components.NewsList
import com.example.newzapp.ui.screens.sources.SourcesScreen

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(
) {
  val navController = rememberNavController()
  Scaffold(topBar = {
    TopAppBar(title = {
      Text("Newz App")
    }, colors = TopAppBarDefaults.mediumTopAppBarColors(
      containerColor = MaterialTheme.colorScheme.primaryContainer
    ))
  },
    bottomBar = {
      NewzBottomNavBar(navController)
    }) {
    Column(
      modifier = Modifier
        .padding(it)
        .fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      NewzNavigation(navHostController = navController)
    }
  }
}

@Composable
fun HandleNewsSourcesResponse(newsSources: NetworkResult<NewsSourcesEntity>?) {
  when (newsSources) {
    is NetworkResult.Error -> {
      Text(text = newsSources.message ?: "", style = MaterialTheme.typography.headlineMedium)
    }

    is NetworkResult.Loading -> {
      CircularProgressIndicator(
        modifier = Modifier.size(80.dp),
        color = Color.Blue,
        strokeWidth = 6.dp
      )
    }

    is NetworkResult.Success -> {
      SourcesScreen(sourcesItems = newsSources.data?.sources?.filterNotNull() ?: emptyList())
    }

    null -> {
      CircularProgressIndicator(
        modifier = Modifier.size(80.dp),
        color = Color.Blue,
        strokeWidth = 6.dp
      )
    }
  }
}


@Composable
fun HandleAllNewsByQueryResponse(
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
      CircularProgressIndicator(
        modifier = Modifier.size(80.dp),
        color = Color.Blue,
        strokeWidth = 6.dp
      )
    }
  }
}

@Composable
fun HandleTopHeadlinesResponse(
  allNews: NetworkResult<TopHeadlinesEntity>?,
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
        newsItems = allNews.data?.toAllNews()?.articles?.filterNotNull() ?: emptyList(),
      )
    }

    null -> {
      CircularProgressIndicator(
        modifier = Modifier.size(80.dp),
        color = Color.Blue,
        strokeWidth = 6.dp
      )
    }
  }
}