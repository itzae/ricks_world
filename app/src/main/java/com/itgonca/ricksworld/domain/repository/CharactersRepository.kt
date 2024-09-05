package com.itgonca.ricksworld.domain.repository

import com.itgonca.ricksworld.domain.model.Character
import kotlinx.coroutines.flow.Flow


interface CharactersRepository {
    fun getAllCharacters(): Flow<List<Character>>
    fun getCharacterDetail(idCharacter: String): Flow<Character>
}