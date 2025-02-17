package com.example.mypokedex.ui.input_events

sealed class InputEvents {
    data object CheckForFirstTime: InputEvents()
    data object OnFirstTimeFinish: InputEvents()
    data object OnCallApi: InputEvents()
}