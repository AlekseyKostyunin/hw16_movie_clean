package com.alekseykostyunin.hw16_movie_clean.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alekseykostyunin.hw16_movie_clean.domain.GetMovieUseCase
import com.alekseykostyunin.hw16_movie_clean.entity.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Initial)
    val state = _state.asStateFlow()

    private val _movie = MutableStateFlow<Movie?>(null)
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