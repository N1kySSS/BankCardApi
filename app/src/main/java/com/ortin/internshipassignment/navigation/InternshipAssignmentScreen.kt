package com.ortin.internshipassignment.navigation

import androidx.compose.runtime.Immutable

@Immutable
sealed class InternshipAssignmentScreen(val route: String) {
    data object MainScreenFlow: InternshipAssignmentScreen(route = "Main")
}
