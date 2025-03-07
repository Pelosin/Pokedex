package com.example.mypokedex.data.remote.model

import com.google.gson.annotations.SerializedName

data class TypeListResponse(
    @SerializedName("results")
    val types: List<TypeResponse>
)
