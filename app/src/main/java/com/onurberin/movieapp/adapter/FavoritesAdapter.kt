package com.onurberin.movieapp.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Movie
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.drawToBitmap
import androidx.recyclerview.widget.RecyclerView
import com.onurberin.movieapp.R
import com.onurberin.movieapp.adapter.FavoritesAdapter.FavoritesHolder
import com.onurberin.movieapp.data.remote.MovieDTO
import org.w3c.dom.Text
import com.onurberin.movieapp.databinding.FavoritesRecyclerItemBinding
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class FavoritesAdapter(private val favoriteList: List<MovieDTO>): RecyclerView.Adapter<FavoritesHolder>() {


    class FavoritesHolder(val binding: FavoritesRecyclerItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesHolder {
        val binding = FavoritesRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesHolder, position: Int) {
        holder.binding.txtFavoritesMovieName.text = favoriteList[position].title
        holder.binding.txtFavoritesImdb.text = favoriteList[position].vote_average.toString()
        holder.binding.txtFavoritesSummary.text = favoriteList[position].overview

        val imageStream = ByteArrayInputStream(favoriteList[position].imgByte)
        val bitmapImage = BitmapFactory.decodeStream(imageStream)
        holder.binding.imgFavorites.setImageBitmap(bitmapImage)

    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }
}