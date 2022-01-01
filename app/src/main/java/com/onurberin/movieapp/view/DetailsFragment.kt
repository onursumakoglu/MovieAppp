package com.onurberin.movieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.onurberin.movieapp.R
import com.onurberin.movieapp.data.remote.MovieDTO
import com.onurberin.movieapp.data.roomdb.MovieDao
import com.onurberin.movieapp.data.roomdb.MovieDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailsFragment : Fragment() {

    private lateinit var v: View
    private lateinit var movieData: MovieDTO
    private lateinit var txtMovieName: TextView
    private lateinit var txtMovieDate: TextView
    private lateinit var txtMovieLanguage: TextView
    private lateinit var txtMovieOverview: TextView
    private lateinit var txtVoteAverage: TextView
    private lateinit var imgMoviePoster: ImageView
    private lateinit var imgLike: ImageView
    private lateinit var db: MovieDatabase
    private lateinit var movieDao: MovieDao
    private val compositeDisposable = CompositeDisposable()


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
        imgLike = view.findViewById(R.id.img_detail_like)
        db = Room.databaseBuilder(requireContext().applicationContext, MovieDatabase::class.java, "Movie").build()
        movieDao = db.movieDao()

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

        imgLike.setOnClickListener {

            compositeDisposable.add(
                movieDao.insert(movieData)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            )

            /*
            val direction = MainFragmentDirections.actionMainFragmentToFavoritesFragment4()
            Navigation.findNavController(v).navigate(direction)
             */

            Snackbar.make(view, "${movieData.title} has been added to your favourites.", Snackbar.LENGTH_LONG).show()

        }

    }

}


