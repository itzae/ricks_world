package com.itgonca.ricksworld.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itgonca.ricksworld.ui.components.CharacterItem
import com.itgonca.ricksworld.ui.state.CharacterState
import com.itgonca.ricksworld.ui.theme.RicksWorldTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier, state: CharacterState = CharacterState()) {

    Scaffold(modifier = modifier.fillMaxSize()) { paddingValues ->
        LazyVerticalGrid(
            modifier = Modifier.padding(paddingValues),
            columns = GridCells.Adaptive(100.dp)
        ) {
            items(state.characters) {
                println("Character UI $it")
                CharacterItem(modifier = Modifier.padding(8.dp), characterUiState = it)
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