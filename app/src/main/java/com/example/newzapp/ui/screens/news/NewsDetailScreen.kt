package com.example.newzapp.ui.screens.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.newzapp.data.local.entities.NewsResponseEntity
import com.example.newzapp.ui.screens.customtab.openChromeTab

@Composable
fun NewsDetailScreen(article: NewsResponseEntity.Article) {
  val context = LocalContext.current
  Column(
    modifier = Modifier
      .padding(horizontal = 16.dp, vertical = 8.dp)
      .verticalScroll(
        rememberScrollState()
      )
  ) {
    Text(text = article.title.toString(), style = MaterialTheme.typography.headlineMedium)
    Spacer(modifier = Modifier.height(8.dp))
    Row(
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(text = article.publishedAt.toString(), style = MaterialTheme.typography.bodyMedium)
      Text(text = article.source?.name.toString(), style = MaterialTheme.typography.bodyMedium)
    }
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = article.description.toString(), style = MaterialTheme.typography.headlineSmall)
    Spacer(modifier = Modifier.height(8.dp))
    Text(
      text = article.content.toString(),
      style = MaterialTheme.typography.bodyLarge,
      overflow = TextOverflow.Visible
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
      text = article.url.toString(),
      style = MaterialTheme.typography.bodyMedium,
      overflow = TextOverflow.Visible,
      modifier = Modifier.clickable {
        openChromeTab(context, article.url.toString())
      },
      color = Color.Blue.copy(alpha = 0.5f),
      fontWeight = FontWeight.SemiBold
    )
  }
}