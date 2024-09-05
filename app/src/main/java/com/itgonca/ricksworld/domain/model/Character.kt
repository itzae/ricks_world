package com.itgonca.ricksworld.domain.model

data class Character(
    val id: String = "",
    val name: String = "",
    val image: String = "",
    val specie: String = "",
    val status: String = "",
    val gender: String = "",
    val origin: String = "",
    val location: String = "",
    val episodes: List<Episode> = emptyList()
)

data class Episode(val name: String = "", val episode: String = "")
