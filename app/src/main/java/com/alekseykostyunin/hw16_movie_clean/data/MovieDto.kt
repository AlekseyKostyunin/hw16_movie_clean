package com.alekseykostyunin.hw16_movie_clean.data

import com.alekseykostyunin.hw16_movie_clean.entity.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDto (
    @Json(name = "id") override val id: Int?,
    @Json(name = "name") override val title: String?,
    @Json(name = "description") override val description: String?,
    @Json(name = "year") override val year: Int?,
    @Json(name = "poster") override val poster: PosterDto,
    @Json(name = "rating") override val rating: RatingDto?
) : Movie
