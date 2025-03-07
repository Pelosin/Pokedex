package com.example.mypokedex.utils

import com.example.mypokedex.R

fun Boolean.toColor(): Int =
    if (this) {
        R.color.transparent_yellow
    } else {
        R.color.transparent_gray
    }
