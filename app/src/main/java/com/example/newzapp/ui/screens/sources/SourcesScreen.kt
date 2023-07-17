package com.example.newzapp.ui.screens.sources

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.newzapp.data.local.entities.NewsSourcesEntity
import com.example.newzapp.ui.screens.sources.components.SourceItem

@Composable
fun SourcesScreen(sourcesItems: List<NewsSourcesEntity.Source>) {
  LazyColumn(
    modifier = Modifier.fillMaxSize()
  ){
    items(sourcesItems){ source ->
      SourceItem(source)
    }
  }
}