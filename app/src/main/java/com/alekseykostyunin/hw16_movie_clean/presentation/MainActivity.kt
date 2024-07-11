package com.alekseykostyunin.hw16_movie_clean.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.alekseykostyunin.hw16_movie_clean.R
import com.alekseykostyunin.hw16_movie_clean.databinding.ActivityMainBinding
import com.alekseykostyunin.hw16_movie_clean.di.DaggerAppComponent
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels {
        DaggerAppComponent.create().mainViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            viewModel.viewModelScope.launch {
                viewModel.reloadMovie()
            }
        }

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is State.Initial -> {
                        binding.progress.isVisible = false
                        binding.cardViewMovie.isVisible = false
                        binding.button.isEnabled = true
                    }

                    is State.Loading -> {
                        binding.progress.isVisible = true
                        binding.cardViewMovie.isVisible = false
                        binding.button.isEnabled = false
                    }

                    is State.Success -> {
                        binding.progress.isVisible = false
                        binding.cardViewMovie.isVisible = true
                        binding.button.isEnabled = true
                    }

                    is State.Error -> {
                        binding.progress.isVisible = false
                        binding.cardViewMovie.isVisible = false
                        binding.button.isEnabled = true
                        Toast.makeText(
                            this@MainActivity,
                            state.textError,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.movie.collect { movie ->
                movie.let {
                    Glide.with(this@MainActivity)
                        .load(movie.poster?.url)
                        .into(binding.imageViewPoster)
                    val rating: Double = movie.rating?.kp ?: 0.0
                    val backgroundId = if (rating >= 7.0) R.drawable.circle_green
                    else if (rating > 5.0) R.drawable.circle_orange
                    else R.drawable.circle_red
                    binding.name.text = movie.title ?: ""
                    binding.year.text = movie.year.toString()
                    binding.textViewRating.text = rating.toString().substring(0, 3)
                    binding.textViewRating.setBackgroundResource(backgroundId)
                }
            }
        }
    }
}