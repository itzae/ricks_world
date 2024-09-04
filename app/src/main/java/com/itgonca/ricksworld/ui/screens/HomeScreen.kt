package com.itgonca.ricksworld.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itgonca.ricksworld.ui.components.CharacterItem
import com.itgonca.ricksworld.ui.theme.RicksWorldTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    Scaffold(modifier = modifier.fillMaxSize()) { paddingValues ->
        LazyVerticalGrid(
            modifier = Modifier.padding(paddingValues),
            columns = GridCells.Adaptive(100.dp)
        ) {
            items(9) {
                CharacterItem(modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    RicksWorldTheme {
        HomeScreen()
    }
}