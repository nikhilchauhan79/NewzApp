package com.example.newzapp.ui.screens.news.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newzapp.data.local.entities.NewsResponseEntity
import com.example.newzapp.utils.DateFormatterUtils

@Composable
fun NewsList(
  newsItems: List<NewsResponseEntity.Article>,
  onNewsArticleSelected: (NewsResponseEntity.Article) -> Unit
) {
  LazyColumn(
  ) {
    items(newsItems) { newsItem ->
      NewsItem(newsItem, onNewsArticleSelected)
    }
  }
}

@Composable
fun NewsItem(
  newsItem: NewsResponseEntity.Article,
  onNewsArticleSelected: (NewsResponseEntity.Article) -> Unit
) {
  Card(
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    shape = RoundedCornerShape(8.dp),
    modifier = Modifier
      .padding(horizontal = 16.dp, vertical = 10.dp)
      .clickable {
        onNewsArticleSelected(newsItem)
      }
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp)
    ) {
      AsyncImage(
        model = newsItem.urlToImage,
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
          .height(240.dp)
          .fillMaxWidth()
      )
      Spacer(modifier = Modifier.height(8.dp))
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Text(
          text = newsItem.description.toString(),
          maxLines = 5,
          style = MaterialTheme.typography.headlineSmall,
        )
      }
      Spacer(modifier = Modifier.height(8.dp))
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Text(
          text = DateFormatterUtils.formatDataTime(newsItem.publishedAt.toString()), style = MaterialTheme.typography.bodyLarge,
          fontWeight = FontWeight.Bold
        )
      }
    }
  }
}

@Preview
@Composable
fun PreviewNewsScreen() {

}