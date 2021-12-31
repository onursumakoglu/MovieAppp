package com.onurberin.movieapp.data.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.onurberin.movieapp.data.remote.MovieDTO

@Database(entities = [MovieDTO::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao() : MovieDao
}