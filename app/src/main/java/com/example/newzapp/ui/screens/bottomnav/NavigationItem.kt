package com.example.newzapp.ui.screens.bottomnav

import androidx.annotation.DrawableRes
import com.example.newzapp.R

sealed class NavigationItem(
  var route: String,
  @DrawableRes var icon: Int,
  var title: String
) {
  object Home : NavigationItem("home", R.drawable.outline_home_24, "Home")
  object Headlines : NavigationItem("headlines", R.drawable.outline_newspaper_24, "Headlines")
  object Sources : NavigationItem("sources", R.drawable.outline_source_24, "Sources")
  object Bookmarks : NavigationItem("book_marks", R.drawable.outline_bookmarks_24, "Bookmarks")
}