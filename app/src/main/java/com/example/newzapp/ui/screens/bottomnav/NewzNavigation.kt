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
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.example.newzapp.constants.Screens
import com.example.newzapp.data.local.entities.NewsResponseEntity
import com.example.newzapp.ui.screens.home.HandleAllNewsByQueryResponse
import com.example.newzapp.ui.screens.home.HandleNewsSourcesResponse
import com.example.newzapp.ui.screens.home.HandleTopHeadlinesResponse
import com.example.newzapp.ui.screens.news.NewsDetailScreen
import com.example.newzapp.ui.viewmodels.NewsViewModel

@Composable
fun NewzNavigation(
  navHostController: NavHostController, newsViewModel: NewsViewModel = hiltViewModel()
) {
  val allNews = newsViewModel.allNewsByQueryResponse.collectAsStateWithLifecycle().value
  val topHeadlines = newsViewModel.topHeadlinesResponse.collectAsStateWithLifecycle().value
  val newsSources = newsViewModel.allNewsSourcesResponse.collectAsStateWithLifecycle().value
  val selectedArticle = newsViewModel.selectedArticle.collectAsStateWithLifecycle().value
  val onNewsArticleSelected: (NewsResponseEntity.Article) -> Unit = {
    newsViewModel.selectedArticle.value = it
    navHostController.navigate(Screens.NEWS_DETAIL)
  }

  NavHost(navController = navHostController, startDestination = NavigationItem.Home.route) {
    composable(NavigationItem.Home.route) {
      HandleAllNewsByQueryResponse(allNews, onNewsArticleSelected)
    }

    composable(NavigationItem.Headlines.route) {
      HandleTopHeadlinesResponse(allNews = topHeadlines, onNewsArticleSelected)
    }

    composable(NavigationItem.Sources.route) {
      HandleNewsSourcesResponse(newsSources)
    }

    composable(NavigationItem.Bookmarks.route) {
      NotImplemented()
    }

    composable(Screens.NEWS_DETAIL) {
      if (selectedArticle != null) {
        NewsDetailScreen(article = selectedArticle)
      }
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