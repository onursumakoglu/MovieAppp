package com.onurberin.movieapp.adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.onurberin.movieapp.adapter.FavoritesAdapter.FavoritesHolder
import com.onurberin.movieapp.data.remote.MovieDTO
import com.onurberin.movieapp.data.roomdb.DatabaseProcesses
import com.onurberin.movieapp.data.roomdb.MovieDatabase
import com.onurberin.movieapp.databinding.FavoritesRecyclerItemBinding
import java.io.ByteArrayInputStream

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

        holder.binding.imgOptions.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(it.context)
            alertDialogBuilder.setTitle("Delete")
            //alertDialogBuilder.setIcon(R.drawable....)
            alertDialogBuilder.setMessage("Do you want to remove ${favoriteList[position].title} from your favourites?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                    DatabaseProcesses.deleteDataFromRoom(it.context, favoriteList[position])
                    Toast.makeText(it.context, "${favoriteList[position].title} removed from your favourites.", Toast.LENGTH_LONG).show()
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                })
            alertDialogBuilder.show()
        }
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }
}