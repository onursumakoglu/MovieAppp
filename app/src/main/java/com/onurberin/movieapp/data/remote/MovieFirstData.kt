package com.onurberin.movieapp.data.remote

import com.google.gson.annotations.SerializedName

data class MovieFirstData (

    @SerializedName("page")
    var page: Int? = null,

    @SerializedName("results")
    var results: List<MovieDTO>

)