package com.itgonca.ricksworld.ui.state

import com.itgonca.ricksworld.domain.model.Character

data class CharacterUiState(
    val id: String = "",
    val name: String = "",
    val image: String = "",
    val specie: String = ""
)

data class CharacterState(val characters: List<CharacterUiState> = emptyList())

fun Character.toCharacterUi() =
    CharacterUiState(id = id, name = name, image = image, specie = specie)
