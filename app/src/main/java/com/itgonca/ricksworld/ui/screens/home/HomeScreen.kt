package com.itgonca.ricksworld.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.itgonca.ricksworld.R
import com.itgonca.ricksworld.ui.components.CharacterItem
import com.itgonca.ricksworld.ui.components.ScreenState
import com.itgonca.ricksworld.ui.components.ScreenStateTemplate
import com.itgonca.ricksworld.ui.state.CharacterUiState
import com.itgonca.ricksworld.ui.theme.RicksWorldTheme
import com.itgonca.ricksworld.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreenRoute(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit = {}
) {
    val search by homeViewModel.characterState.collectAsStateWithLifecycle()

    HomeScreen(
        list = search,
        onSearch = { homeViewModel.onUpdateQuery(it) },
        onNavigate = { onNavigate(it) })
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    list: List<CharacterUiState> = emptyList(),
    onNavigate: (String) -> Unit = {},
    onSearch: (String) -> Unit = {}
) {

    var query by rememberSaveable {
        mutableStateOf("")
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CharacterSearchBar(
                query = query,
                onQueryChange = {
                    query = it
                    onSearch(it)
                }
            )
        }) { paddingValues ->
        if (list.isEmpty())
            ScreenStateTemplate(state = ScreenState.EMPTY_DATA)
        else
            LazyVerticalGrid(
                modifier = Modifier.padding(paddingValues),
                columns = GridCells.Adaptive(100.dp)
            ) {
                items(list) {
                    CharacterItem(
                        modifier = Modifier.padding(RicksWorldTheme.dimens.space8),
                        characterUiState = it
                    ) { idCharacter -> onNavigate(idCharacter) }
                }
            }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(RicksWorldTheme.dimens.space16), contentAlignment = Alignment.Center
    ) {
        SearchBar(modifier = Modifier,
            query = query,
            onQueryChange = onQueryChange,
            placeholder = { Text(text = stringResource(id = R.string.search_bar_placeholder)) },
            onSearch = {},
            active = false,
            onActiveChange = {}) {

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