package com.onurberin.movieapp.data.remote

import android.view.View
import android.widget.Toast
import com.onurberin.movieapp.adapter.MovieAdapter
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance{
    companion object{
        private const val BASE_URL: String = "https://api.themoviedb.org/3/"
        fun getRetroInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}