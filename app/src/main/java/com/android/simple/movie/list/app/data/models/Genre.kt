package com.android.simple.movie.list.app.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

class Genre(
    @SerializedName("uuid") val uuid: UUID,
    @SerializedName("name") val name: String
)