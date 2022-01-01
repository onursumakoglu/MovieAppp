package com.onurberin.movieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.onurberin.movieapp.data.remote.MovieDTO
import com.onurberin.movieapp.data.roomdb.MovieDao
import com.onurberin.movieapp.data.roomdb.MovieDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.ByteArrayOutputStream
import android.graphics.Bitmap
import androidx.core.view.drawToBitmap
import com.onurberin.movieapp.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    private lateinit var movieData: MovieDTO
    private lateinit var db: MovieDatabase
    private lateinit var movieDao: MovieDao
    private val compositeDisposable = CompositeDisposable()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = Room.databaseBuilder(requireContext().applicationContext, MovieDatabase::class.java, "Movie").build()
        movieDao = db.movieDao()

        arguments?.let {
            movieData = DetailsFragmentArgs.fromBundle(it).movieData
            binding.txtMovieName.text = movieData.title
            binding.txtMovieOverviewFromapi.text = "Overview: " + movieData.overview
            binding.txtImdbFromapi.text = movieData.vote_average.toString()
            Glide
                .with(view)
                .load("https://image.tmdb.org/t/p/original" + movieData.poster_path)
                .centerCrop()
                //.placeholder(R.drawable.loading_spinner)
                .into(binding.imgDetail)
        }

        binding.imgDetailLike.setOnClickListener {
            val baos = ByteArrayOutputStream()
            val bitmap = binding.imgDetail.drawToBitmap()
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


