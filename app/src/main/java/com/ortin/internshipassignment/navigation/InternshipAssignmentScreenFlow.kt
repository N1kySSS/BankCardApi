package com.ortin.internshipassignment.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun InternshipAssignmentScreenFlow(
    padding: PaddingValues,
    navController: NavHostController
) {
    NavHost(
        modifier = Modifier.padding(padding),
        navController = navController,
        startDestination = InternshipAssignmentScreen.MainScreenFlow.route
    ) {
        mainScreenFlow()
    }
}
