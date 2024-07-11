package com.alekseykostyunin.hw16_movie_clean.domain

import com.alekseykostyunin.hw16_movie_clean.data.MovieDto
import com.alekseykostyunin.hw16_movie_clean.data.MovieRepository
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend fun execute(): MovieDto {
        return movieRepository.getMovie()
    }
}