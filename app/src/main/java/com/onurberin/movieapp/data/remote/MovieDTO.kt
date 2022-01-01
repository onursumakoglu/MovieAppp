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
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    var release_date: String?,
    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    var original_language: String?,
    @SerializedName("poster_path")
    var poster_path: String?,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var imgByte: ByteArray?



) : Parcelable, Serializable {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}