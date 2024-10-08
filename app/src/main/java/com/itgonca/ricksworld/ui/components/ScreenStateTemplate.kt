package com.itgonca.ricksworld.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.itgonca.ricksworld.R
import com.itgonca.ricksworld.ui.theme.RicksWorldTheme
import com.itgonca.ricksworld.ui.theme.Typography

@Composable
fun ScreenStateTemplate(
    modifier: Modifier = Modifier,
    state: ScreenState,
    onErrorAction: () -> Unit = {}
) {
    val image = when (state) {
        ScreenState.EMPTY_DATA -> R.drawable.il_not_found
        ScreenState.ERROR -> R.drawable.il_error_state
    }
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "${state.name} screen"
            )
            Text(text = state.message, style = Typography.titleMedium)
            if (state == ScreenState.ERROR)
                Button(modifier = Modifier.padding(RicksWorldTheme.dimens.space16), onClick = { onErrorAction() }) {
                    Text(text = "Regresar")
                }
        }
    }
}

enum class ScreenState(val message: String) {
    EMPTY_DATA("No se encontró información"), ERROR("Ha ocurrido un error, intenta más tarde")
}

@Preview(showBackground = true)
@Composable
private fun ScreenStateTemplatePreview() {
    RicksWorldTheme {
        ScreenStateTemplate(state = ScreenState.ERROR)
    }
}