package com.example.newzapp.ui.screens.bottomnav

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newzapp.ui.screens.home.HandleAllNewsByQueryResponse
import com.example.newzapp.ui.screens.home.HandleNewsSourcesResponse
import com.example.newzapp.ui.screens.home.HandleTopHeadlinesResponse
import com.example.newzapp.ui.viewmodels.NewsViewModel

@Composable
fun NewzNavigation(
  navHostController: NavHostController, newsViewModel: NewsViewModel = hiltViewModel()
) {
  val allNews = newsViewModel.allNewsByQueryResponse.collectAsStateWithLifecycle().value
  val topHeadlines = newsViewModel.topHeadlinesResponse.collectAsStateWithLifecycle().value
  val newsSources = newsViewModel.allNewsSourcesResponse.collectAsStateWithLifecycle().value
  NavHost(navController = navHostController, startDestination = NavigationItem.Home.route) {
    composable(NavigationItem.Home.route) {
      HandleAllNewsByQueryResponse(allNews)
    }

    composable(NavigationItem.Headlines.route) {
      HandleTopHeadlinesResponse(allNews = topHeadlines)
    }

    composable(NavigationItem.Sources.route) {
      HandleNewsSourcesResponse(newsSources)
    }

    composable(NavigationItem.Bookmarks.route) {
      NotImplemented()
    }
  }
}

@Composable
private fun NotImplemented() {
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(text = "Not Yet implemented", style = MaterialTheme.typography.headlineMedium)
  }
}