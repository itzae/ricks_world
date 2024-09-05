package com.itgonca.ricksworld.data

import com.apollographql.apollo.ApolloClient
import com.itgonca.ricksworld.GetAllUsersQuery
import com.itgonca.ricksworld.GetCharacterByNameQuery
import com.itgonca.ricksworld.GetCharacterDetailQuery
import com.itgonca.ricksworld.di.IoDispatcher
import com.itgonca.ricksworld.domain.model.Character
import com.itgonca.ricksworld.domain.model.Episode
import com.itgonca.ricksworld.domain.repository.CharactersRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CharactersRepository {
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
    }.flowOn(ioDispatcher)

    override fun getCharacterDetail(idCharacter: String): Flow<Character> = flow {
        val result =
            apolloClient.query(GetCharacterDetailQuery(idCharacter)).execute().dataOrThrow()
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
    }.flowOn(ioDispatcher)

    override fun getCharacterByName(name: String): Flow<List<Character>> = flow<List<Character>> {
        val result = apolloClient.query(GetCharacterByNameQuery(name)).execute().dataOrThrow()
        val listCharacters = result.characters?.results?.map { character ->
            Character(
                id = character?.id ?: "",
                name = character?.name ?: "",
                image = character?.image ?: "",
                specie = character?.species ?: ""
            )
        } ?: emptyList()
        emit(listCharacters)
    }.flowOn(ioDispatcher)

}