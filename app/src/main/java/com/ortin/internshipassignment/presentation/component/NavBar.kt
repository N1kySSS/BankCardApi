package com.ortin.internshipassignment.presentation.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ortin.internshipassignment.ui.theme.Light
import com.ortin.internshipassignment.ui.theme.Primary

@Composable
fun NavBar(navController: NavHostController) {
    val screens: List<NavigationBarItem> = listOf(
        NavigationBarItem.Home,
        NavigationBarItem.Card
    )
    val navigationBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navigationBackStackEntry?.destination?.route
    val indicatorColor = Color(0xff2f2f29)

    NavigationBar {
        screens.forEach { item: NavigationBarItem ->
            NavigationBarItem(
                selected = (currentRoute == item.route),
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(NavigationBarItem.Home.route) {
                                inclusive = false
                                saveState = false
                            }

                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = item.title,
                        tint = if (currentRoute != item.route) Primary else Light
                    )
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = indicatorColor
                )
            )
        }
    }
}
