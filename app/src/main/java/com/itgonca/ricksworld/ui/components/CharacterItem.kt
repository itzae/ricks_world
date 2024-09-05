package com.itgonca.ricksworld.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.itgonca.ricksworld.ui.state.CharacterUiState
import com.itgonca.ricksworld.ui.theme.RicksWorldTheme

@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    characterUiState: CharacterUiState = CharacterUiState(),
    onItemSelect: (String) -> Unit = {}
) {
    Box(modifier = modifier
        .size(100.dp)
        .clickable { onItemSelect(characterUiState.id) }) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = characterUiState.image,
            contentDescription = "${characterUiState.name} image",
            contentScale = ContentScale.FillBounds
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(color = Color(0x80000000)),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = characterUiState.name,
                color = Color.White,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CharacterItemPreview() {
    RicksWorldTheme {
        CharacterItem()
    }
}