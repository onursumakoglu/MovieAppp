package com.onurberin.movieapp.adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Movie
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.drawToBitmap
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.onurberin.movieapp.R
import com.onurberin.movieapp.adapter.FavoritesAdapter.FavoritesHolder
import com.onurberin.movieapp.data.remote.MovieDTO
import com.onurberin.movieapp.data.roomdb.MovieDatabase
import org.w3c.dom.Text
import com.onurberin.movieapp.databinding.FavoritesRecyclerItemBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
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

        holder.binding.imgOptions.setOnClickListener {

            val alertDialogBuilder = AlertDialog.Builder(it.context)
            alertDialogBuilder.setTitle("Delete")
            //alertDialogBuilder.setIcon(R.drawable....)
            alertDialogBuilder.setMessage("Do you want to remove ${favoriteList[position].title} from your favourites?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                    val compositeDisposable = CompositeDisposable()
                    val db = Room.databaseBuilder(it.context, MovieDatabase::class.java, "Movie").build()
                    val movieDao = db.movieDao()
                    compositeDisposable.add(
                        movieDao.delete(favoriteList[position])
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe()
                    )
                    Toast.makeText(it.context, "${favoriteList[position].title} removed from your favourites.", Toast.LENGTH_LONG).show()
                    //val direction = FavoritesFragmentDirections.actionFavoritesFragmentSelf()
                    //Navigation.findNavController(it).navigate(direction)
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