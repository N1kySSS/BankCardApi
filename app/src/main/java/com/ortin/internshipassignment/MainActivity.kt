package com.ortin.internshipassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.ortin.internshipassignment.navigation.InternshipAssignmentScreenFlow
import com.ortin.internshipassignment.presentation.component.NavBar
import com.ortin.internshipassignment.presentation.screen.CardScreen
import com.ortin.internshipassignment.ui.theme.InternshipAssignmentTheme
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinContext {
                InternshipAssignmentTheme {
                    val navController = rememberNavController()

                    Scaffold(
                        bottomBar = {
                            NavBar(navController = navController)
                        }
                    ) { paddings ->
                        InternshipAssignmentScreenFlow(
                            padding = paddings,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}
