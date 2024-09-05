package com.itgonca.ricksworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.itgonca.ricksworld.ui.navigation.MainGraph
import com.itgonca.ricksworld.ui.theme.RicksWorldTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RicksWorldTheme {
                MainGraph(rememberNavController())
            }
        }
    }
}