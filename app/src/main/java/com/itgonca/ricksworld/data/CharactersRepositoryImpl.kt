package com.itgonca.ricksworld.data

import com.apollographql.apollo.ApolloClient
import com.itgonca.ricksworld.GetAllUsersQuery
import com.itgonca.ricksworld.domain.model.Character
import com.itgonca.ricksworld.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(private val apolloClient: ApolloClient) :
    CharactersRepository {
    override fun getAllCharacters(): Flow<List<Character>> = flow {
        val result = apolloClient.query(GetAllUsersQuery()).execute()
        emit(result.data?.characters?.results?.map { character ->
            Character(
                id = character?.id ?: "",
                name = character?.name ?: "",
                image = character?.image ?: "",
                specie = character?.species ?: ""
            )
        } ?: emptyList())
    }.catch {
        println("Error fetch ${it.message}")
    }

    override fun getCharactersByFilter(idCharacter: String): Flow<Int> {
        TODO("Not yet implemented")
    }

    override fun getCharacterDetail(): Flow<Int> {
        TODO("Not yet implemented")
    }
}