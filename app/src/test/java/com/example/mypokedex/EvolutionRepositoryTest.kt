package com.example.mypokedex

import EvolutionChain
import EvolutionResponse
import com.example.mypokedex.data.remote.model.SpeciesResponse
import com.example.mypokedex.data.remote.repository.EvolutionRepository
import com.example.mypokedex.data.remote.source.PokemonDataSource
import com.example.mypokedex.data.utils.FunctionResponse
import com.example.mypokedex.domain.model.Evolution
import com.example.mypokedex.ui.mapper.EvolutionChainMapper
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
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

class EvolutionRepositoryTest {

    @MockK
    lateinit var pokemonDataSource: PokemonDataSource

    @MockK
    lateinit var evolutionChainMapper: EvolutionChainMapper

    @MockK
    lateinit var expectedListOfEvolutions: List<Evolution>

    @MockK
    lateinit var expectedEvolutionChain: EvolutionChain

    @MockK
    lateinit var expectedEvolutionResponse: EvolutionResponse

    @MockK
    lateinit var expectedSpecies: SpeciesResponse

    @InjectMockKs
    lateinit var victim: EvolutionRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    // Test for getting Evolution Chain successfully
    @Test
    fun `getEvolutionChain success`() = runTest {
        val name = "bulbasaur"
        val speciesResponse = FunctionResponse.Success(expectedSpecies)
        val evolutionResponse = FunctionResponse.Success(expectedEvolutionResponse)

        every { expectedSpecies.speciesEvolutionUrl.url } returns ""

        coEvery { pokemonDataSource.getSpecies(name) } returns speciesResponse
        coEvery { pokemonDataSource.getEvolutionChain("1") } returns evolutionResponse
        every { evolutionChainMapper(any()) } returns expectedListOfEvolutions

        val result = victim.getEvolutionChain(name)

        assert(result is FunctionResponse.Success)
        assertEquals(expectedListOfEvolutions, (result as FunctionResponse.Success).data)

        coVerify(exactly = 1) {
            pokemonDataSource.getSpecies(name)
            pokemonDataSource.getEvolutionChain("1")
            evolutionChainMapper(any())
        }
    }
}