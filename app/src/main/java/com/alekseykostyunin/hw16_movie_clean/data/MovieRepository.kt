package com.alekseykostyunin.hw16_movie_clean.data

import javax.inject.Inject

class MovieRepository @Inject constructor() {

    suspend fun getMovie(): MovieDto {
        return try {
            Retrofit.movieInfoApi.getMovie()
        } catch (e: Exception) {
            throw Exception("Error")
        }
    }
}