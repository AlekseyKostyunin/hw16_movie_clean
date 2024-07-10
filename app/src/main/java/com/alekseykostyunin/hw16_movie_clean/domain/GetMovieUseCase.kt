package com.alekseykostyunin.hw16_movie_clean.domain

import com.alekseykostyunin.hw16_movie_clean.data.MovieRepository
import com.alekseykostyunin.hw16_movie_clean.entity.Movie

class GetMovieUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute(): Movie {
        return movieRepository.getMovie()
    }
}