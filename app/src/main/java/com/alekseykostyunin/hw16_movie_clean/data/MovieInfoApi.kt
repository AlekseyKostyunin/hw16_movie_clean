package com.alekseykostyunin.hw16_movie_clean.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL = "https://api.kinopoisk.dev/"

object Retrofit {
    private val moshi: Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val movieInfoApi: MovieInfoApi = retrofit.create(MovieInfoApi::class.java)
}

interface MovieInfoApi {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @GET("v1.4/movie/random?token=VRY7JS6-4TB4C8P-MR49NYR-PDYB8KE&notNullFields=poster.url")
    suspend fun getMovie(): MovieDto
}