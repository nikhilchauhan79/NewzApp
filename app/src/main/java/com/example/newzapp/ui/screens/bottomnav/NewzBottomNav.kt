package com.example.newzapp.ui.screens.bottomnav

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NewzBottomNavBar(navController: NavHostController) {
  val bottomNavItems = listOf<NavigationItem>(
    NavigationItem.Home,
    NavigationItem.Headlines,
    NavigationItem.Sources,
    NavigationItem.Bookmarks,
  )

  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = navBackStackEntry?.destination?.route

  BottomAppBar {
    bottomNavItems.forEach { item ->
      NavigationBarItem(selected = currentRoute == item.route, onClick = {
        navController.navigate(item.route) {
          navController.graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
              saveState = true
            }
          }
          launchSingleTop = true
          restoreState = true
        }
      }, label = {
        Text(item.title)
      }, icon = {
        Icon(painter = painterResource(id = item.icon), contentDescription = item.title)
      }, alwaysShowLabel = true)
    }
  }
}

@Preview
@Composable
fun PreviewBottomNavBar() {
//  NewzBottomNavBar(navController)
}