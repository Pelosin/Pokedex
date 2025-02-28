package com.example.mypokedex

import com.example.mypokedex.common.PokemonDetailsTest
import com.example.mypokedex.data.remote.api.PokeApi
import com.example.mypokedex.data.remote.source.PokemonDataSource
import com.example.mypokedex.data.utils.FunctionResponse
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.domain.model.PokemonDetails
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PokemonDataSourceTest {

    @MockK
    lateinit var pokeApi: PokeApi

    @MockK
    lateinit var pokemonDetailsFunctionResponse: FunctionResponse<PokemonDetails>

    @MockK
    lateinit var expectedPokemonDetails : PokemonDetails

    @MockK
    lateinit var retrofitDetailsResponse : Response<PokemonDetails>

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

        assertEquals(victim.getPokemonByName(expectedPokemonDetails.name).data,
            expectedPokemonDetails)

        coVerify(exactly = 1) {
            pokeApi.getPokemonByName(expectedPokemonDetails.name)
            retrofitDetailsResponse.body()
        }


    }

    @Test
    fun validateGetPokemonByNameRetrofitFailure() = runTest{
        val expectedMessage = "Error"
        every { expectedPokemonDetails.name } returns ""

        every { retrofitDetailsResponse.body() } returns null

        every { retrofitDetailsResponse.isSuccessful } returns false

        every { retrofitDetailsResponse.message() } returns expectedMessage

        every { pokemonDetailsFunctionResponse.message } returns ""

        coEvery {
            pokeApi.getPokemonByName(expectedPokemonDetails.name)
        } returns retrofitDetailsResponse

        assertEquals(victim.getPokemonByName(expectedPokemonDetails.name).message,
            expectedMessage)

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

        every { retrofitDetailsResponse.message() } returns expectedMessage

        every { pokemonDetailsFunctionResponse.message } returns ""

        coEvery {
            pokeApi.getPokemonByName(expectedPokemonDetails.name)
        } returns retrofitDetailsResponse

        assertEquals(victim.getPokemonByName(expectedPokemonDetails.name).message,
            expectedMessage)

        coVerify(exactly = 1) {
            pokeApi.getPokemonByName(expectedPokemonDetails.name)
            retrofitDetailsResponse.body()
            retrofitDetailsResponse.message()
            retrofitDetailsResponse.isSuccessful
        }
    }

}