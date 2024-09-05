package com.itgonca.ricksworld.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itgonca.ricksworld.domain.repository.CharactersRepository
import com.itgonca.ricksworld.ui.state.CharacterState
import com.itgonca.ricksworld.ui.state.CharacterUiState
import com.itgonca.ricksworld.ui.state.toCharacterUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val charactersRepository: CharactersRepository) :
    ViewModel() {

    private val _query = MutableStateFlow("")

    val query: StateFlow<String>
        get() = _query.asStateFlow()

    private val characterListState: StateFlow<CharacterState> =
        charactersRepository.getAllCharacters().map { characterResponse ->
            CharacterState(characters = characterResponse.map { it.toCharacterUi() })
        }.catch {
            CharacterState(isError = true)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CharacterState()
        )

    @OptIn(FlowPreview::class)
    val characterState = query
        .debounce(300)
        .combine(characterListState) { text, characters ->
            if (text.isBlank())
                characters.characters
            else {

                charactersRepository.getCharacterByName(text).first()
                    .map { it.toCharacterUi() }
            }
        }.catch {
            emptyList<CharacterUiState>()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = characterListState.value.characters
        )

    fun onUpdateQuery(querys: String) {
        _query.update { querys }
    }
}