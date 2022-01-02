package com.onurberin.movieapp.view

import android.R
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
import android.view.MenuItem
import androidx.core.view.drawToBitmap
import androidx.navigation.Navigation
import com.onurberin.movieapp.data.roomdb.DatabaseProcesses
import com.onurberin.movieapp.databinding.FragmentDetailsBinding
import java.io.DataInput


class DetailsFragment : Fragment() {

    private lateinit var movieData: MovieDTO
    private lateinit var movieDao: MovieDao
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

        arguments?.let {
            movieData = DetailsFragmentArgs.fromBundle(it).movieData
            binding.txtMovieName.text = movieData.title
            binding.txtMovieOverviewFromapi.text = "Overview: " + movieData.overview
            binding.txtMovieReleaseDateFromapi.text = movieData.release_date
            binding.txtMovieLanguageFromapi.text = movieData.original_language
            binding.txtImdbFromapi.text = movieData.vote_average.toString()
            Glide
                .with(view)
                .load("https://image.tmdb.org/t/p/original" + movieData.poster_path)
                .centerCrop()
                .into(binding.imgDetail)
        }

        binding.imgDetailLike.setOnClickListener {
            val bitmap = binding.imgDetail.drawToBitmap()
            DatabaseProcesses.insertDataToRoom(view, movieData, bitmap)

        }

        binding.imageViewBack.setOnClickListener {
            val direction = DetailsFragmentDirections.actionDetailsFragmentToMainFragment()
            Navigation.findNavController(it).navigate(direction)
        }

    }

}


