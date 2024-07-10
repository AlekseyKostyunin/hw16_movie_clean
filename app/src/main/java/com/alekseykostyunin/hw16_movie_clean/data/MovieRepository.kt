package com.alekseykostyunin.hw16_movie_clean.data

import android.util.Log
import com.alekseykostyunin.hw16_movie_clean.entity.Movie
import com.alekseykostyunin.hw16_movie_clean.presentation.State

class MovieRepository() {

    suspend fun getMovie(): Movie {
        var movieDto: MovieDto? = null
        try {
            movieDto = Retrofit.movieInfoApi.getMovie().body()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Log.d("TEST_Movie", movieDto.toString())
        return movieDto as Movie
    }

    suspend fun getMovie2(): Movie {
        val movieDto = try {
             Retrofit.movieInfoApi.getMovie().body()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return movieDto as Movie
    }
}