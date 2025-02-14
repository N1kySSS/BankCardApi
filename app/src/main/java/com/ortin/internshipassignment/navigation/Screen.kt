package com.ortin.internshipassignment.navigation

sealed class Screen(val route: String) {
    data object CardScreen : Screen("card_screen")

    data object HistoryScreen : Screen("history_screen")
}
