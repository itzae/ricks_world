package com.itgonca.ricksworld.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itgonca.ricksworld.domain.repository.CharactersRepository
import com.itgonca.ricksworld.ui.state.CharacterState
import com.itgonca.ricksworld.ui.state.toCharacterUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val charactersRepository: CharactersRepository) :
    ViewModel() {
    private val _characterState = MutableStateFlow(CharacterState())
    val characterState: StateFlow<CharacterState>
        get() = _characterState.asStateFlow()

    fun fetchAllCharacters() {
        charactersRepository.getAllCharacters().onEach { characterDomain ->
            _characterState.update {
                it.copy(characters = characterDomain.map { character -> character.toCharacterUi() })
            }
        }.launchIn(viewModelScope)
    }
}