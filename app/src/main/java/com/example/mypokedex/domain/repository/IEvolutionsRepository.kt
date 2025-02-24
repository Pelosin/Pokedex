package com.example.mypokedex.domain.repository

import EvolutionChain
import com.example.mypokedex.data.utils.FunctionResponse

interface IEvolutionsRepository {
    suspend fun getEvolutionChain(id : String) : FunctionResponse<EvolutionChain>
}