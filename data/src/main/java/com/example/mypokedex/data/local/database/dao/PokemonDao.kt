package com.example.mypokedex.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mypokedex.data.local.database.model.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(vararg pokemon: PokemonEntity)

    @Query("SELECT * FROM pokemon WHERE name = :name")
    fun getPokemonByName(name: String): Flow<PokemonEntity>

    @Query("SELECT * FROM pokemon")
    fun getAllPokemons(): Flow<List<PokemonEntity>>

    @Query("SELECT * FROM pokemon WHERE favorite = 1")
    fun getFavoritePokemons(): Flow<List<PokemonEntity>>

    @Update
    fun updatePokemon(pokemon: PokemonEntity)

    @Query("DELETE FROM pokemon WHERE name = :name")
    fun deletePokemon(name: String)

    @Query("DELETE FROM pokemon")
    fun deleteAllPokemons()

    @Query(
        "UPDATE pokemon SET favorite " +
                "= CASE WHEN favorite = 1 THEN 0 ELSE 1 END" +
                " WHERE name = :name"
    )
    fun favoritePokemonByName(name: String)
}