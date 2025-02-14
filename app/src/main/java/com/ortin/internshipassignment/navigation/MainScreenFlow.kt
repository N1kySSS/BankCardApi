package com.ortin.internshipassignment.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.ortin.internshipassignment.presentation.screen.CardScreen
import com.ortin.internshipassignment.presentation.screen.HistoryScreen

fun NavGraphBuilder.mainScreenFlow() {
    navigation(
        startDestination = Screen.CardScreen.route,
        route = InternshipAssignmentScreen.MainScreenFlow.route,
    ) {
        composable(
            route = Screen.CardScreen.route,
            enterTransition = { fadeIn(animationSpec = tween(0)) },
            exitTransition = { fadeOut(animationSpec = tween(0)) }
        ) {
            CardScreen()
        }
        composable(
            route = Screen.HistoryScreen.route,
            enterTransition = { fadeIn(animationSpec = tween(0)) },
            exitTransition = { fadeOut(animationSpec = tween(0)) }
        ) {
            HistoryScreen()
        }
    }
}
