package com.onurberin.movieapp.data.remote

import com.google.gson.annotations.SerializedName

data class MovieDTO (

    @SerializedName("title") var title: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("vote_average") var vote_average: Double? = null,
    @SerializedName("poster_path") var poster_path: String? = null
)