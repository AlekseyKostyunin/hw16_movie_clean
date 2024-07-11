package com.alekseykostyunin.hw16_movie_clean.entity

import com.alekseykostyunin.hw16_movie_clean.data.PosterDto
import com.alekseykostyunin.hw16_movie_clean.data.RatingDto

interface Movie {
    val id: Int?
    val title: String?
    val description: String?
    val year: Int?
    val poster: PosterDto?
    val rating: RatingDto?
}