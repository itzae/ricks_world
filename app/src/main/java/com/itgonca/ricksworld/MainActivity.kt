package com.itgonca.ricksworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.itgonca.ricksworld.ui.screens.HomeScreen
import com.itgonca.ricksworld.ui.theme.RicksWorldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RicksWorldTheme {
                HomeScreen()
            }
        }
    }
}