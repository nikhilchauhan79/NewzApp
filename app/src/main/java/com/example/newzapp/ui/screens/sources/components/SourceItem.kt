package com.example.newzapp.ui.screens.sources.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.newzapp.data.local.entities.NewsSourcesEntity
import com.example.newzapp.ui.screens.customtab.openChromeTab

@Composable
fun SourceItem(source: NewsSourcesEntity.Source) {
  val context = LocalContext.current
  Card(
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    shape = RoundedCornerShape(8.dp),
    modifier = Modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .padding(horizontal = 16.dp, vertical = 10.dp)
      .clickable {
        openChromeTab(context, source.url.toString())
      }
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(vertical = 8.dp, horizontal = 8.dp)
    ) {
      Text(
        text = source.name.toString(), style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold
      )
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = source.description.toString(), style = MaterialTheme.typography.bodyLarge,
        maxLines = 8, overflow = TextOverflow.Ellipsis
      )
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = source.url.toString(), style = MaterialTheme.typography.bodyLarge,
        color = Color.Blue, textDecoration = TextDecoration.Underline
      )

      Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
      ) {
        Text(text = source.country.toString(), style = MaterialTheme.typography.bodyLarge)
        Text(text = source.language.toString(), style = MaterialTheme.typography.bodyLarge)
      }
    }
  }
}