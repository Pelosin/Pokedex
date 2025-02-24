package com.example.mypokedex.data.remote.model

import com.google.gson.annotations.SerializedName

data class SpeciesResponse(
    @SerializedName("evolution_chain")
    val speciesEvolutionUrl : EvolutionUrl
)

data class EvolutionUrl(
    val url : String
)