package com.example.mypokedex.utils

import com.example.mypokedex.data.remote.model.StatsResponse
import com.example.mypokedex.domain.model.Stats

fun StatsResponse.toStats() =
    Stats(
        this.statType.name,
        this.baseStat.toString()
    )