package com.example.mypokedex.ui.mapper

import EvolutionChain
import com.example.mypokedex.data.utils.Constants
import com.example.mypokedex.domain.model.Evolution

class EvolutionChainMapper {
    fun map(evolutionChain: EvolutionChain): List<String> {
        val evolutionQueue = ArrayList<Evolution>()
        evolutionQueue.add(Evolution(
            evolutionChain.species.name,
            Constants.Network.retrieveImageUrl(Constants.Network.retrieveUrlId(evolutionChain.species.url))
        ))

        var evolvesTo = evolutionChain.evolvesTo
        while (evolvesTo.isNotEmpty()) {
            evolvesTo.forEach {
                evolutionQueue.add(it.species.name)
            }
            evolvesTo = evolvesTo[0].evolvesTo
        }

        return evolutionQueue

    }
}