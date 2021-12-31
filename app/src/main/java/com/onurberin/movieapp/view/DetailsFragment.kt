package com.onurberin.movieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.onurberin.movieapp.R
import com.onurberin.movieapp.data.remote.MovieDTO

class DetailsFragment : Fragment() {

    private lateinit var v: View
    private lateinit var movieData: MovieDTO
    private lateinit var txtMovieName: TextView
    private lateinit var txtMovieDate: TextView
    private lateinit var txtMovieLanguage: TextView
    private lateinit var txtMovieOverview: TextView
    private lateinit var txtVoteAverage: TextView
    private lateinit var imgMoviePoster: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view
        txtMovieName = view.findViewById(R.id.txt_movie_name)
        txtMovieOverview = view.findViewById(R.id.txt_movie_overview_fromapi)
        txtMovieDate = view.findViewById(R.id.txt_movie_release_date_fromapi)
        txtMovieLanguage = view.findViewById(R.id.txt_movie_language_fromapi)
        txtVoteAverage = view.findViewById(R.id.txt_imdb_fromapi)
        imgMoviePoster = view.findViewById(R.id.img_detail)


        arguments?.let {
            movieData = DetailsFragmentArgs.fromBundle(it).movieData
            txtMovieName.text = movieData.title
            txtMovieOverview.text = "Overview: " + movieData.overview
            txtVoteAverage.text = movieData.vote_average.toString()
            Glide
                .with(view)
                .load("https://image.tmdb.org/t/p/original" + movieData.poster_path)
                .centerCrop()
                //.placeholder(R.drawable.loading_spinner)
                .into(imgMoviePoster);

        }

        }

    }


