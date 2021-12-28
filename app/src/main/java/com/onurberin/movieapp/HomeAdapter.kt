package com.onurberin.movieapp

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class HomeAdapter(val movieList: ArrayList<String>):
    RecyclerView.Adapter<HomeAdapter.MoviesHolder>() {

    class MoviesHolder(view: View): RecyclerView.ViewHolder(view) {
        var txtMovieName: TextView = view.findViewById(R.id.home_grid_movie_name)

        //fun bind(movie: MovieModel)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}