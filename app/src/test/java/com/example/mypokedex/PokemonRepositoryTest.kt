package com.example.mypokedex

import com.example.mypokedex.data.repository.PokemonRepository
import com.example.mypokedex.data.remote.source.PokemonDataSource
import com.example.mypokedex.data.utils.FunctionResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PokemonRepositoryTest {

    @MockK
    lateinit var pokemonDataSourceMock: PokemonDataSource

    @MockK
    lateinit var functionResponse: FunctionResponse<List<PokemonListItem>>

    @InjectMockKs
    lateinit var victim: PokemonRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun validateGetAllPokemon() = runTest {
        val listOfPokemonListItems = listOf(PokemonListItem("Buba", "a"))

        every { functionResponse.data } returns listOfPokemonListItems
        coEvery { pokemonDataSourceMock.getAllPokemon() } returns functionResponse
        val response = victim.fetchPokemonList()

        coVerify(exactly = 1) {
            victim.fetchPokemonList()
            pokemonDataSourceMock.getAllPokemon()
        }

        assertEquals(response.data, listOfPokemonListItems)
    }

    @Test
    fun `test getAllPokemon Error`() = runTest {
        val errorMessage = "APIError"

        every { functionResponse.message } returns errorMessage
        coEvery { pokemonDataSourceMock.getAllPokemon() } returns functionResponse
        val response = victim.fetchPokemonList()

        coVerify(exactly = 1) {
            victim.fetchPokemonList()
            pokemonDataSourceMock.getAllPokemon()
        }

        assertEquals(response.message, errorMessage)
    }
}