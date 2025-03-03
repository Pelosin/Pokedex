package com.example.mypokedex

import com.example.mypokedex.data.remote.repository.PokemonDetailsRepository
import com.example.mypokedex.data.remote.source.PokemonDataSource
import com.example.mypokedex.data.utils.FunctionResponse
import com.example.mypokedex.domain.model.PokemonDetails
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

class PokemonDetailsRepositoryTest {

    @MockK
    lateinit var pokemonDataSourceMock: PokemonDataSource

    @MockK
    lateinit var functionResponse: FunctionResponse<PokemonDetails>

    @MockK
    lateinit var expectedPokemonDetails: PokemonDetails

    @InjectMockKs
    lateinit var victim: PokemonDetailsRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test getPokemonByName Success`() = runTest {
        val expectedName = "Gengar"
        every { expectedPokemonDetails.name } returns expectedName

        every { functionResponse.data } returns expectedPokemonDetails

        coEvery {
            pokemonDataSourceMock.getPokemonByName(expectedName)
        } returns functionResponse

        val response = victim.getPokemonByName(expectedName)

        coVerify(exactly = 1) {
            victim.getPokemonByName(expectedName)
            pokemonDataSourceMock.getPokemonByName(expectedName)
        }

        assertEquals(response.data, expectedPokemonDetails)
    }

    @Test
    fun `test getAllPokemon Error`() = runTest {
        val expectedName = "Gengar"
        val errorMessage = "APIError"

        every { functionResponse.message } returns errorMessage
        coEvery {
            pokemonDataSourceMock.getPokemonByName(expectedName)
        } returns functionResponse
        val response = victim.getPokemonByName(expectedName)

        coVerify(exactly = 1) {
            victim.getPokemonByName(expectedName)
            pokemonDataSourceMock.getPokemonByName(expectedName)
        }

        assertEquals(response.message, errorMessage)
    }

}