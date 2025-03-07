package com.example.mypokedex.utils

import EvolutionChain
import com.example.mypokedex.domain.model.Evolution

fun EvolutionChain.toEvolutionList(): List<Evolution> {
    val evolutionQueue = ArrayList<Evolution>()
    evolutionQueue.add(
        Evolution(
            this.species.name,
            this.species.url
                .retrieveUrlId()
                .retrieveImageUrl()
        )
    )

    var evolvesTo = this.evolvesTo
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