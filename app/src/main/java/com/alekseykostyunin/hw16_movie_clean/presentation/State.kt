package com.alekseykostyunin.hw16_movie_clean.presentation

sealed class State {
    data object Initial : State()
    data object Loading : State()
    data class Error(val textError: String = "Ошибка загруки") : State()
    data object Success : State()
}