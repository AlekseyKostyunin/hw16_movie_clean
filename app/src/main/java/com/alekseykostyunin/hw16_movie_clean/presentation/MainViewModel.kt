package com.alekseykostyunin.hw16_movie_clean.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alekseykostyunin.hw16_movie_clean.data.MovieDto
import com.alekseykostyunin.hw16_movie_clean.data.PosterDto
import com.alekseykostyunin.hw16_movie_clean.data.RatingDto
import com.alekseykostyunin.hw16_movie_clean.domain.GetMovieUseCase
import com.alekseykostyunin.hw16_movie_clean.entity.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Initial)
    val state = _state.asStateFlow()

    private val defaultMovie = MovieDto(0,"","", 0, PosterDto(""), RatingDto(1.0))

    private val _movie = MutableStateFlow<Movie>(defaultMovie)
    val movie = _movie.asStateFlow()

    init {
        viewModelScope.launch {
            reloadMovie()
        }
    }

    suspend fun reloadMovie() {
        _state.value = State.Loading
        _movie.value = getMovieUseCase.execute()
        _state.value = State.Success
    }
}