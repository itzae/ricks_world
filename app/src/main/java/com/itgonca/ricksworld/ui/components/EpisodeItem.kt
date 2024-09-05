package com.itgonca.ricksworld.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itgonca.ricksworld.ui.theme.RicksWorldTheme
import com.itgonca.ricksworld.ui.theme.Typography

@Composable
fun EpisodeItem(modifier: Modifier = Modifier, title: String, subtitle: String) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = Typography.titleMedium)
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = subtitle,
                style = Typography.labelLarge
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun EpisodeItemPreview(modifier: Modifier = Modifier) {
    RicksWorldTheme {
        EpisodeItem(title = "Test", subtitle = "Test")
    }
}