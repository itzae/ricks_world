package com.itgonca.ricksworld.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itgonca.ricksworld.domain.repository.CharactersRepository
import com.itgonca.ricksworld.ui.state.CharacterState
import com.itgonca.ricksworld.ui.state.toCharacterUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val charactersRepository: CharactersRepository) :
    ViewModel() {
    val characterListState: StateFlow<CharacterState> =
        charactersRepository.getAllCharacters().map { characterResponse ->
            CharacterState(characters = characterResponse.map { it.toCharacterUi() })
        }.catch {
            CharacterState(isError = true)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CharacterState()
        )
}