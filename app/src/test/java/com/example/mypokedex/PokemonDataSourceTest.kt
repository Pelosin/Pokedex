package com.example.mypokedex

import EvolutionResponse
import com.example.mypokedex.data.remote.api.PokeApi
import com.example.mypokedex.data.remote.model.PokemonResponse
import com.example.mypokedex.data.remote.model.SpeciesResponse
import com.example.mypokedex.data.remote.source.PokemonDataSource
import com.example.mypokedex.data.utils.FunctionResponse
import com.example.mypokedex.data.remote.model.PokemonDetails
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class PokemonDataSourceTest {

    @MockK
    lateinit var pokeApi: PokeApi

    @MockK
    lateinit var pokemonDetailsFunctionResponse: FunctionResponse<PokemonDetails>

    @MockK
    lateinit var expectedPokemonDetails: PokemonDetails

    @MockK
    lateinit var retrofitDetailsResponse: Response<PokemonDetails>

    @InjectMockKs
    lateinit var victim: PokemonDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun validateGetPokemonByNameSuccess() = runTest {
        every { expectedPokemonDetails.name } returns ""

        every { retrofitDetailsResponse.body() } returns expectedPokemonDetails

        every { retrofitDetailsResponse.isSuccessful } returns true

        every { pokemonDetailsFunctionResponse.data } returns expectedPokemonDetails

        coEvery { pokeApi.getPokemonByName(expectedPokemonDetails.name) } returns retrofitDetailsResponse

        assertEquals(
            victim.getPokemonByName(expectedPokemonDetails.name).data,
            expectedPokemonDetails
        )

        coVerify(exactly = 1) {
            pokeApi.getPokemonByName(expectedPokemonDetails.name)
            retrofitDetailsResponse.body()
        }


    }

    @Test
    fun validateGetPokemonByNameRetrofitFailure() = runTest {
        val expectedMessage = "Error"
        every { expectedPokemonDetails.name } returns ""

        every { retrofitDetailsResponse.body() } returns null

        every { retrofitDetailsResponse.isSuccessful } returns false

        every { retrofitDetailsResponse.message() } returns expectedMessage

        every { pokemonDetailsFunctionResponse.message } returns ""

        coEvery {
            pokeApi.getPokemonByName(expectedPokemonDetails.name)
        } returns retrofitDetailsResponse

        assertEquals(
            victim.getPokemonByName(expectedPokemonDetails.name).message,
            expectedMessage
        )

        coVerify(exactly = 1) {
            pokeApi.getPokemonByName(expectedPokemonDetails.name)
            retrofitDetailsResponse.body()
            retrofitDetailsResponse.message()
            retrofitDetailsResponse.isSuccessful
        }
    }

    @Test
    fun validateGetPokemonByNameThrowException() = runTest {
        val expectedMessage = "Error"
        every { expectedPokemonDetails.name } returns ""

        every { retrofitDetailsResponse.body() } returns null

        every { retrofitDetailsResponse.isSuccessful } returns false

        every { retrofitDetailsResponse.message() } returns ""

        every { pokemonDetailsFunctionResponse.message } returns expectedMessage

        coEvery {
            pokeApi.getPokemonByName(expectedPokemonDetails.name)
        } answers {
            throw Exception(expectedMessage)
        }

        assertEquals(
            victim.getPokemonByName(expectedPokemonDetails.name).message,
            expectedMessage
        )

        coVerify(exactly = 1) {
            pokeApi.getPokemonByName(expectedPokemonDetails.name)

        }
        coVerify(exactly = 0) {
            retrofitDetailsResponse.body()
            retrofitDetailsResponse.message()
            retrofitDetailsResponse.isSuccessful
        }
    }

    @MockK
    lateinit var expectedPokedexFunctionResponse: FunctionResponse<List<PokemonListItem>>

    @MockK
    lateinit var expectedPokedex: List<PokemonListItem>

    @MockK
    lateinit var expectedPokemonResponse: PokemonResponse

    @MockK
    lateinit var retrofitPokedexResponse: Response<PokemonResponse>

    @Test
    fun validateGetAllPokemonSuccess() = runTest {
        every { expectedPokedexFunctionResponse.data } returns expectedPokedex

        every { retrofitPokedexResponse.body() } returns expectedPokemonResponse

        every { retrofitPokedexResponse.isSuccessful } returns true

        every { expectedPokemonResponse.results } returns expectedPokedex

        coEvery { pokeApi.getAllPokemon() } returns retrofitPokedexResponse

        assertEquals(victim.getAllPokemon().data, expectedPokedex)

        coVerify(exactly = 1) {
            pokeApi.getAllPokemon()
            retrofitPokedexResponse.body()
            retrofitPokedexResponse.isSuccessful
            expectedPokemonResponse.results
        }
    }

    @Test
    fun `test getAllPokemon error`() = runTest {
        val apiResponse = mockk<Response<PokemonResponse>>()
        coEvery { pokeApi.getAllPokemon() } returns apiResponse
        every { apiResponse.body() } returns null
        every { apiResponse.isSuccessful } returns false
        every { apiResponse.message() } returns "API Error"

        val result = victim.getAllPokemon()

        assert(result is FunctionResponse.Error)
        assertEquals((result as FunctionResponse.Error).message, "API Error")

        coVerify(exactly = 1) { pokeApi.getAllPokemon() }
    }

    @Test
    fun `test getAllPokemon exception`() = runTest {
        coEvery { pokeApi.getAllPokemon() } throws IOException("Network error")

        val result = victim.getAllPokemon()

        assert(result is FunctionResponse.Error)
        assertEquals((result as FunctionResponse.Error).message, "Network error")

        coVerify(exactly = 1) { pokeApi.getAllPokemon() }
    }

    @Test
    fun `test getEvolutionChain success`() = runTest {
        val id = "1"
        val evolutionResponse = mockk<EvolutionResponse>()
        val apiResponse = mockk<Response<EvolutionResponse>>()
        coEvery { pokeApi.getEvolutionChain(id) } returns apiResponse
        every { apiResponse.isSuccessful } returns true
        every { apiResponse.body() } returns evolutionResponse

        val result = victim.getEvolutionChain(id)

        assert(result is FunctionResponse.Success)
        assertEquals((result as FunctionResponse.Success).data, evolutionResponse)

        coVerify(exactly = 1) {
            pokeApi.getEvolutionChain(id)
        }
    }

    @Test
    fun `test getEvolutionChain error`() = runTest {
        val id = "1"
        val apiResponse = mockk<Response<EvolutionResponse>>()
        coEvery { pokeApi.getEvolutionChain(id) } returns apiResponse
        every { apiResponse.body() } returns null
        every { apiResponse.isSuccessful } returns false
        every { apiResponse.message() } returns "API Error"

        val result = victim.getEvolutionChain(id)

        assert(result is FunctionResponse.Error)
        assertEquals((result as FunctionResponse.Error).message, "API Error")

        coVerify(exactly = 1) { pokeApi.getEvolutionChain(id) }
    }

    @Test
    fun `test getEvolutionChain exception`() = runTest {
        val id = "1"
        coEvery { pokeApi.getEvolutionChain(id) } throws IOException("Network error")

        val result = victim.getEvolutionChain(id)

        assert(result is FunctionResponse.Error)
        assertEquals((result as FunctionResponse.Error).message, "Network error")

        coVerify(exactly = 1) { pokeApi.getEvolutionChain(id) }
    }

    @Test
    fun `test getSpecies success`() = runTest {
        val name = "pikachu"
        val speciesResponse = mockk<SpeciesResponse>()
        val apiResponse = mockk<Response<SpeciesResponse>>()
        coEvery { pokeApi.getSpecies(name) } returns apiResponse
        every { apiResponse.isSuccessful } returns true
        every { apiResponse.body() } returns speciesResponse

        val result = victim.getSpecies(name)

        assert(result is FunctionResponse.Success)
        assertEquals((result as FunctionResponse.Success).data, speciesResponse)

        coVerify(exactly = 1) { pokeApi.getSpecies(name) }
    }

    @Test
    fun `test getSpecies error`() = runTest {
        val name = "pikachu"
        val apiResponse = mockk<Response<SpeciesResponse>>()
        coEvery { pokeApi.getSpecies(name) } returns apiResponse
        every { apiResponse.body() } returns null
        every { apiResponse.isSuccessful } returns false
        every { apiResponse.message() } returns "API Error"

        val result = victim.getSpecies(name)

        assert(result is FunctionResponse.Error)
        assertEquals((result as FunctionResponse.Error).message, "API Error")

        coVerify(exactly = 1) { pokeApi.getSpecies(name) }
    }

    @Test
    fun `test getSpecies exception`() = runTest {
        val name = "pikachu"
        coEvery { pokeApi.getSpecies(name) } throws IOException("Network error")

        val result = victim.getSpecies(name)

        assert(result is FunctionResponse.Error)
        assertEquals((result as FunctionResponse.Error).message, "Network error")

        coVerify(exactly = 1) { pokeApi.getSpecies(name) }
    }
}