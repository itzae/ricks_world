package com.itgonca.ricksworld.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itgonca.ricksworld.ui.state.StatusCharacter
import com.itgonca.ricksworld.ui.theme.RicksWorldTheme

@Composable
fun DotStatus(modifier: Modifier = Modifier, statusCharacter: StatusCharacter) {
    val color = when(statusCharacter){
        StatusCharacter.ALIVE -> Color.Green
        StatusCharacter.DEAD -> Color.Red
        StatusCharacter.UNKNOWN -> Color.LightGray
    }
    Box(
        modifier = modifier
            .size(10.dp)
            .background(
                color = color,
                shape = CircleShape
            )
    )
}

@Composable
@Preview(showBackground = true)
fun DotStatusPreview() {
    RicksWorldTheme {
        DotStatus(statusCharacter = StatusCharacter.DEAD)
    }
}


