package com.itgonca.ricksworld.ui.state

import com.itgonca.ricksworld.domain.model.Character


data class CharacterState(
    val characters: List<CharacterUiState> = emptyList(),
    val isError: Boolean = false,
    val query:String=""
)

data class CharacterUiState(
    val id: String = "",
    val name: String = "",
    val image: String = "",
    val specie: String = ""
)

fun Character.toCharacterUi() =
    CharacterUiState(id = id, name = name, image = image, specie = specie)
