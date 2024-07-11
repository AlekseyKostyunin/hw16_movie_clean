package com.alekseykostyunin.hw16_movie_clean.di

import com.alekseykostyunin.hw16_movie_clean.presentation.MainViewModelFactory
import dagger.Component

@Component
interface AppComponent {
    fun mainViewModelFactory(): MainViewModelFactory
}