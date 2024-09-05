package com.itgonca.ricksworld.data

import com.apollographql.apollo.ApolloClient
import com.itgonca.ricksworld.GetAllUsersQuery
import com.itgonca.ricksworld.GetCharacterDetailQuery
import com.itgonca.ricksworld.domain.model.Character
import com.itgonca.ricksworld.domain.model.Episode
import com.itgonca.ricksworld.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(private val apolloClient: ApolloClient) :
    CharactersRepository {
    override fun getAllCharacters(): Flow<List<Character>> = flow {
        val result = apolloClient.query(GetAllUsersQuery()).execute()
        val character = result.data?.characters?.results?.map { character ->
            Character(
                id = character?.id ?: "",
                name = character?.name ?: "",
                image = character?.image ?: "",
                specie = character?.species ?: ""
            )
        } ?: emptyList()

        emit(character)
  }

    override fun getCharacterDetail(idCharacter: String): Flow<Character> = flow {
        val result = apolloClient.query(GetCharacterDetailQuery("idCharacter")).execute().dataOrThrow()
        val characterDetail = with(result.character) {
            Character(
                name = this?.name ?: "",
                image = this?.image ?: "",
                status = this?.status ?: "",
                specie = this?.species ?: "",
                gender = this?.gender ?: "",
                origin = this?.origin?.name ?: "",
                location = this?.location?.name ?: "",
                episodes = this?.episode?.map {
                    Episode(name = it?.name ?: "", episode = it?.episode ?: "")
                } ?: emptyList()
            )
        }

        emit(characterDetail)
    }

}