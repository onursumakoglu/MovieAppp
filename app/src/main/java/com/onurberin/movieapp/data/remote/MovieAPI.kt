package com.onurberin.movieapp.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey : String,
        @Query("page") page : Int
    ) : Call<MovieFirstData>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey : String = "787e75e639e0896dcb4ffe2f44545b43",
        @Query("page") page : Int
    ) : Call<MovieFirstData>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey : String = "787e75e639e0896dcb4ffe2f44545b43",
        @Query("page") page : Int
    ) : Call<MovieFirstData>

}


// base url: https://api.themoviedb.org/3/
// api_key=787e75e639e0896dcb4ffe2f44545b43
