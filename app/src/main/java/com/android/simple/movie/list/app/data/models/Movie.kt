package com.android.simple.movie.list.app.data.models

import java.util.*

data class Movie(
    val uuid: UUID,
    val title: String,
    val directors: List<String>,
    val year: String,
    val genres: List<String>
)