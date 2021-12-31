package com.onurberin.movieapp.data.remote

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity(tableName = "Movie")
@Parcelize
data class MovieDTO (

    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String?,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    var overview: String?,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    var vote_average: Double?,

    @SerializedName("poster_path")
    var poster_path: String? = null


) : Parcelable, Serializable {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}