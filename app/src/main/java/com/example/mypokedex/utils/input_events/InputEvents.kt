package com.example.mypokedex.utils.input_events

sealed class InputEvents {
    data object OnFirstTimeFinish: InputEvents()
    data object OnCallApi: InputEvents()
}