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
import com.onurberin.movieapp.databinding.HomeRecyclerItemBinding
import com.onurberin.movieapp.view.MainFragmentDirections

class HomeMovieAdapter(private val movieDTO: List<MovieDTO>):
    RecyclerView.Adapter<HomeMovieAdapter.MoviesHolder>() {

    var aPosition: Int = 0

    inner class MoviesHolder(val binding: HomeRecyclerItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        val binding = HomeRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {

        holder.binding.homeGridMovieName.text = movieDTO[position].title
        holder.binding.homeGridMoviePoint.text = movieDTO[position].vote_average.toString()
        Glide
            .with(holder.binding.root)
            .load("https://image.tmdb.org/t/p/original" + movieDTO[position].poster_path)
            .centerCrop()
            .into(holder.binding.homeGridMovieImage);

        holder.binding.root.setOnClickListener {
            aPosition = holder.adapterPosition
            if (aPosition != RecyclerView.NO_POSITION){
                val direction = MainFragmentDirections.actionMainFragmentToDetailsFragment(movieData = movieDTO[aPosition])
                Navigation.findNavController(it).navigate(direction)
            }
        }

    }

    override fun getItemCount(): Int {
        return movieDTO.size
    }
}