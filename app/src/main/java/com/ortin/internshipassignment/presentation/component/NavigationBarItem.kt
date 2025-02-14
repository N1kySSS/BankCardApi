package com.ortin.internshipassignment.presentation.component

import com.ortin.internshipassignment.R
import com.ortin.internshipassignment.navigation.Screen

sealed class NavigationBarItem(
    val route: String,
    val title: String,
    val icon: Int
) {
    data object Home : NavigationBarItem(
        route = Screen.CardScreen.route,
        title = "Home",
        icon = R.drawable.home
    )

    data object Card : NavigationBarItem(
        route = Screen.HistoryScreen.route,
        title = "History",
        icon = R.drawable.card
    )
}
