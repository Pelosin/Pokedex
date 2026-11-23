package com.example.mypokedex.utils

import com.example.mypokedex.data.remote.model.TypeResponse
import com.example.mypokedex.domain.model.Type

fun TypeResponse.toType() = Type(
    this.name,
    this.url.retrieveUrlId().retrieveTypeImageUrl()
)