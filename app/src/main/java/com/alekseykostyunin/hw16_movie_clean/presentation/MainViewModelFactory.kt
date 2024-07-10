package com.alekseykostyunin.hw16_movie_clean.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alekseykostyunin.hw16_movie_clean.data.MovieRepository
import com.alekseykostyunin.hw16_movie_clean.domain.GetMovieUseCase

class MainViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            val movieRepository = MovieRepository()
            return MainViewModel(GetMovieUseCase(movieRepository)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}