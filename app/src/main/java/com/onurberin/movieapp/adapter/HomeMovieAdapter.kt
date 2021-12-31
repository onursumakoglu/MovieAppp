package com.onurberin.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onurberin.movieapp.R
import com.onurberin.movieapp.data.remote.MovieDTO
import com.onurberin.movieapp.view.MainFragmentDirections

class HomeMovieAdapter(private val movieDTO: List<MovieDTO>):
    RecyclerView.Adapter<HomeMovieAdapter.MoviesHolder>() {

    private lateinit var view: View
    var aPos: Int = 0

    inner class MoviesHolder(var view: View): RecyclerView.ViewHolder(view) {
        var txtMovieName: TextView = view.findViewById(R.id.home_grid_movie_name)
        var txtMoviePoint: TextView = view.findViewById(R.id.home_grid_movie_point)
        var imgMoviePoster: ImageView = view.findViewById(R.id.home_grid_movie_image)

        fun bind(movieDTO: MovieDTO){
            txtMovieName.text = movieDTO.title
            txtMoviePoint.text = movieDTO.vote_average.toString()
            Glide
                .with(view)
                .load("https://image.tmdb.org/t/p/original" + movieDTO.poster_path)
                .centerCrop()
                //.placeholder(R.drawable.loading_spinner)
                .into(imgMoviePoster);


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.home_recycler_item, parent, false)
        return MoviesHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.bind(movieDTO[position])

        holder.view.setOnClickListener {

            aPos = holder.adapterPosition

            if (aPos != RecyclerView.NO_POSITION){
                val direction = MainFragmentDirections.actionMainFragmentToDetailsFragment(movieData = movieDTO[aPos])
                Navigation.findNavController(it).navigate(direction)
            }

        }
    }

    override fun getItemCount(): Int {
        return movieDTO.size
    }
}