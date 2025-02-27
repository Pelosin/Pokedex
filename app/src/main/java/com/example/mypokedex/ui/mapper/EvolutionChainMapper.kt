package com.example.mypokedex.ui.mapper

import EvolutionChain
import com.example.mypokedex.domain.model.Evolution
import com.example.mypokedex.utils.retrieveImageUrl
import com.example.mypokedex.utils.retrieveUrlId
import javax.inject.Inject

class EvolutionChainMapper @Inject constructor() {
    operator fun invoke(evolutionChain: EvolutionChain) : List<Evolution> {
        val evolutionQueue = ArrayList<Evolution>()
        evolutionQueue.add(
            Evolution(
                evolutionChain.species.name,
                evolutionChain.species.url
                    .retrieveUrlId()
                    .retrieveImageUrl()
            )
        )

        var evolvesTo = evolutionChain.evolvesTo
        while (evolvesTo.isNotEmpty()) {
            evolvesTo.forEach {
                evolutionQueue.add(
                    Evolution(
                        it.species.name,
                        it.species.url
                            .retrieveUrlId()
                            .retrieveImageUrl()
                    )
                )
            }
            evolvesTo = evolvesTo[0].evolvesTo
        }

        return evolutionQueue
    }
}