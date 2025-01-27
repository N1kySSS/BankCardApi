package com.ortin.internshipassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
                    CardScreen()
                }
            }
        }
    }
}
