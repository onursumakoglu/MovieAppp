package com.onurberin.movieapp.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class  MovieDTO (

    @SerializedName("title") var title: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("vote_average") var vote_average: Double? = null,
    @SerializedName("poster_path") var poster_path: String? = null


   // @SerializedName("original_language") var original_language: String? = null,
   // @SerializedName("release_date") var release_date: String? = null



) : Parcelable