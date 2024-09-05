package com.itgonca.ricksworld.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.itgonca.ricksworld.ui.components.DotStatus
import com.itgonca.ricksworld.ui.components.EpisodeItem
import com.itgonca.ricksworld.ui.state.CharacterDetailInfo
import com.itgonca.ricksworld.ui.state.CharacterDetailState
import com.itgonca.ricksworld.ui.theme.RicksWorldTheme
import com.itgonca.ricksworld.ui.theme.Typography
import com.itgonca.ricksworld.ui.viewmodel.CharacterDetailViewModel


@Composable
fun CharacterDetailScreenRoute(
    idCharacter: String = "",
    detailViewModel: CharacterDetailViewModel = hiltViewModel(),
    onBack: () -> Unit = {}
) {
    val stateDetail: CharacterDetailState by detailViewModel.characterDetailInfo.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = idCharacter) {
        detailViewModel.fetchDetailCharacter(idCharacter)
    }
    
    when (stateDetail) {
        CharacterDetailState.Loading -> {}
        is CharacterDetailState.Success -> {
            CharacterDetailScreen(
                state = (stateDetail as CharacterDetailState.Success).state,
                onBack = onBack
            )
        }

        CharacterDetailState.Error -> {}
    }
}

@Composable
fun CharacterDetailScreen(
    modifier: Modifier = Modifier,
    state: CharacterDetailInfo = CharacterDetailInfo(),
    onBack: () -> Unit = {}
) {

    val size = with(LocalDensity.current) { 300f.toDp() }

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (banner, avatar, content) = createRefs()

        BannerDetail(
            modifier = Modifier.constrainAs(banner) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            onBack = onBack
        )

        CharacterAvatar(
            modifier = Modifier.constrainAs(avatar) {
                top.linkTo(banner.bottom)
                bottom.linkTo(banner.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            size = size,
            urlImage = state.image,
            nameCharacter = state.name
        )

        CharacterData(
            modifier = Modifier.constrainAs(content) {
                top.linkTo(avatar.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
            },
            state = state
        )
    }
}

@Composable
private fun BannerDetail(modifier: Modifier = Modifier, onBack: () -> Unit = {}) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
            )
    ) {
        IconButton(onClick = onBack, modifier = Modifier.padding(top = 16.dp)) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "",
                tint = Color.White
            )
        }
    }
}

@Composable
private fun CharacterAvatar(
    modifier: Modifier = Modifier,
    size: Dp,
    urlImage: String,
    nameCharacter: String
) {
    AsyncImage(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(Color.LightGray),
        model = urlImage,
        contentDescription = "$nameCharacter avatar"

    )
}

@Composable
private fun CharacterData(modifier: Modifier = Modifier, state: CharacterDetailInfo) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = state.name, style = Typography.headlineLarge, fontWeight = FontWeight.Bold)
        Row(verticalAlignment = Alignment.CenterVertically) {
            DotStatus(statusCharacter = state.status)
            Text(
                modifier = Modifier.padding(8.dp),
                text = "${state.status.label} - ${state.specie}",
                style = Typography.titleSmall
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(16.dp),
            text = "Comes from: ${state.origin}",
            style = Typography.labelMedium
        )
        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp),
            text = "Last known location: ${state.location}",
            style = Typography.labelMedium
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Episodes",
            style = Typography.titleSmall
        )
        LazyColumn {
            items(state.episodes) {
                EpisodeItem(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    title = it.number,
                    subtitle = it.name
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun CharacterDetailScreenPreview() {
    RicksWorldTheme {
        CharacterDetailScreen(state = CharacterDetailInfo(name = "Rick"))
    }
}
