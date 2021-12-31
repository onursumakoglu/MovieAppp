package com.onurberin.movieapp.data.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.onurberin.movieapp.data.remote.MovieDTO
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie")
    fun getAll() : Flowable<List<MovieDTO>>

    @Insert
    fun insert(movieDTO: MovieDTO) : Completable

    @Delete
    fun delete(movieModel: MovieDTO) : Completable
}