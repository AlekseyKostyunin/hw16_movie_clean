package com.alekseykostyunin.hw16_movie_clean.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RatingDto(
    @Json(name = "kp") val kp: Double?
)