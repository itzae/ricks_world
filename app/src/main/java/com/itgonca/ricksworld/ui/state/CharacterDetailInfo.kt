package com.itgonca.ricksworld.ui.state


sealed class CharacterDetailState {
    data object Loading : CharacterDetailState()
    data class Success(val state: CharacterDetailInfo) : CharacterDetailState()
    data object Error : CharacterDetailState()
}

data class CharacterDetailInfo(
    val name: String = "",
    val status: StatusCharacter = StatusCharacter.UNKNOWN,
    val image: String = "",
    val genre: String = "",
    val specie: String = "",
    val location: String = "",
    val origin: String = "",
    val episodes: List<EpisodeUi> = emptyList()
)

data class EpisodeUi(val name: String, val number: String)
enum class StatusCharacter(val label: String) {
    ALIVE("Alive"), DEAD("Dead"), UNKNOWN("Unknown")
}