package com.itgonca.ricksworld.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itgonca.ricksworld.domain.repository.CharactersRepository
import com.itgonca.ricksworld.ui.state.CharacterDetailInfo
import com.itgonca.ricksworld.ui.state.CharacterDetailState
import com.itgonca.ricksworld.ui.state.EpisodeUi
import com.itgonca.ricksworld.ui.state.StatusCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val charactersRepository: CharactersRepository) :
    ViewModel() {

    private val _characterDetailInfo =
        MutableStateFlow<CharacterDetailState>(CharacterDetailState.Loading)
    val characterDetailInfo: StateFlow<CharacterDetailState>
        get() = _characterDetailInfo.asStateFlow()

    fun fetchDetailCharacter(id: String) {
        charactersRepository.getCharacterDetail(id)
            .onEach { characterResponse ->
                println("Success fetch")
                _characterDetailInfo.update {
                    CharacterDetailState.Success(
                        state = CharacterDetailInfo(name = characterResponse.name,
                            image = characterResponse.image,
                            genre = characterResponse.gender,
                            status = StatusCharacter.valueOf(characterResponse.status.uppercase()),
                            specie = characterResponse.specie,
                            location = characterResponse.location,
                            origin = characterResponse.origin,
                            episodes = characterResponse.episodes.map { episode ->
                                EpisodeUi(
                                    name = episode.name,
                                    number = episode.episode
                                )
                            })
                    )
                }
            }.catch {
                _characterDetailInfo.update { CharacterDetailState.Error }
            }.launchIn(viewModelScope)
    }
}