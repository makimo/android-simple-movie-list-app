package com.android.simple.movie.list.app.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

class MovieResponse(
    @SerializedName("uuid") val uuid: UUID,
    @SerializedName("title") val title: String,
    @SerializedName("directors") val directors: List<String>,
    @SerializedName("year") val year: String,
    @SerializedName("genres") val genres: List<UUID>
)
