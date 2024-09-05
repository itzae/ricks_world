package com.itgonca.ricksworld.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.itgonca.ricksworld.ui.components.CharacterItem
import com.itgonca.ricksworld.ui.state.CharacterState
import com.itgonca.ricksworld.ui.theme.RicksWorldTheme
import com.itgonca.ricksworld.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreenRoute(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit = {}
) {
    val state by homeViewModel.characterListState.collectAsStateWithLifecycle()
    HomeScreen(state = state) { onNavigate(it) }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: CharacterState = CharacterState(),
    onNavigate: (String) -> Unit = {}
) {

    Scaffold(modifier = modifier.fillMaxSize()) { paddingValues ->
        LazyVerticalGrid(
            modifier = Modifier.padding(paddingValues),
            columns = GridCells.Adaptive(100.dp)
        ) {
            items(state.characters) {
                CharacterItem(
                    modifier = Modifier.padding(8.dp),
                    characterUiState = it
                ) { idCharacter -> onNavigate(idCharacter) }
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