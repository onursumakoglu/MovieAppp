package com.onurberin.movieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.onurberin.movieapp.R
import com.onurberin.movieapp.adapter.FavoritesAdapter
import com.onurberin.movieapp.data.remote.MovieDTO
import com.onurberin.movieapp.data.roomdb.MovieDatabase
import com.onurberin.movieapp.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class FavoritesFragment : Fragment() {

    private lateinit var v: View
    private lateinit var mRecyclerView: RecyclerView
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view

        mRecyclerView = view.findViewById(R.id.favorites_recycler_view)
        val db = Room.databaseBuilder(requireActivity().applicationContext, MovieDatabase::class.java, "Movie").build()
        val movieDao = db.movieDao()

        compositeDisposable.add(
            movieDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )
    }

    private fun handleResponse(favoriteList : List<MovieDTO>) {
        mRecyclerView.layoutManager = LinearLayoutManager(requireActivity().applicationContext)
        val adapter = FavoritesAdapter(favoriteList)
        mRecyclerView.adapter = adapter

    }

}