package com.onurberin.movieapp.data.roomdb

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.onurberin.movieapp.data.remote.MovieDTO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.ByteArrayOutputStream

class DatabaseProcesses{
    companion object{
        private val compositeDisposable = CompositeDisposable()

        fun deleteDataFromRoom(context: Context?, favoriteMovie: MovieDTO) {
            val db = Room.databaseBuilder(context!!, MovieDatabase::class.java, "Movie").build()
            val movieDao = db.movieDao()
            compositeDisposable.add(
                movieDao.delete(favoriteMovie)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            )
        }

        fun insertDataToRoom(view: View, movieData: MovieDTO, bitmap: Bitmap){
            val db = Room.databaseBuilder(view.context, MovieDatabase::class.java, "Movie").build()
            val movieDao = db.movieDao()
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val byteImage = baos.toByteArray()
            movieData.imgByte = byteImage
            compositeDisposable.add(
                movieDao.insert(movieData)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            )
            Snackbar.make(view, "${movieData.title} has been added to your favourites.", Snackbar.LENGTH_LONG).show()
        }

    }
}