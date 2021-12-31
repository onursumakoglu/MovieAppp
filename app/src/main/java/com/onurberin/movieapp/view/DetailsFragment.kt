package com.onurberin.movieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.onurberin.movieapp.R
import com.onurberin.movieapp.data.remote.MovieDTO

class DetailsFragment : Fragment() {

    private lateinit var v: View
    private lateinit var movieData: MovieDTO
    private lateinit var txtMovie: TextView

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
        txtMovie = view.findViewById(R.id.txt_movie_name)

        arguments?.let {
            movieData = DetailsFragmentArgs.fromBundle(it).movieData
            txtMovie.text = movieData.title
        }

    }


}