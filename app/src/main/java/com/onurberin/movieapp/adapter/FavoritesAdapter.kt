package com.onurberin.movieapp.adapter

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onurberin.movieapp.R
import com.onurberin.movieapp.adapter.FavoritesAdapter.FavoritesHolder
import com.onurberin.movieapp.data.remote.MovieDTO
import org.w3c.dom.Text

class FavoritesAdapter(private val favoriteList: List<MovieDTO>): RecyclerView.Adapter<FavoritesHolder>() {

    private lateinit var view: View

    inner class FavoritesHolder(var view: View): RecyclerView.ViewHolder(view) {
        var txtMovieName: TextView = view.findViewById(R.id.txt_favorites_movie_name)
        var txtMoviePoint: TextView = view.findViewById(R.id.txt_favorites_imdb)
        var txtMovieSummary: TextView = view.findViewById(R.id.txt_favorites_summary)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.favorites_recycler_item, parent, false)
        return FavoritesHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritesHolder, position: Int) {
        holder.txtMovieName.text = favoriteList[position].title
        holder.txtMoviePoint.text = favoriteList[position].vote_average.toString()
        holder.txtMovieSummary.text = favoriteList[position].overview

    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }
}