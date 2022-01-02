package com.onurberin.movieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onurberin.movieapp.R
import com.onurberin.movieapp.adapter.HomeMovieAdapter
import com.onurberin.movieapp.data.remote.MovieAPI
import com.onurberin.movieapp.data.remote.MovieDTO
import com.onurberin.movieapp.data.remote.MovieFirstData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UpcomingFragment : Fragment() {

    private var BASE_URL: String = "https://api.themoviedb.org/3/"
    private var retrofit: Retrofit? = null
    private var movieAPI: MovieAPI? = null
    private var movieCall: retrofit2.Call<MovieFirstData>? = null
    private var movieFirstData: MovieFirstData? = null
    private lateinit var movieDTO: List<MovieDTO>
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var movieAdapter: HomeMovieAdapter
    var numberOfColumns = 3
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.loading_progress)
        progressBar.visibility = View.VISIBLE
        mRecyclerView = view.findViewById(R.id.home_recycler_view)
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        movieAPI = retrofit!!.create(MovieAPI::class.java)

        fetchData()
    }

    private fun fetchData(){
        movieCall = movieAPI!!.getUpcomingMovies()
        movieCall!!.enqueue(object : retrofit2.Callback<MovieFirstData> {
            override fun onResponse(
                call: retrofit2.Call<MovieFirstData>,
                response: Response<MovieFirstData>
            ) {
                if (response.body()!=null){
                    movieFirstData = response.body()
                    movieFirstData?.let {
                        movieDTO = it.results
                        progressBar.visibility = View.INVISIBLE
                        getMovies()
                    }
                }
            }
            override fun onFailure(call: retrofit2.Call<MovieFirstData>, t: Throwable) {
                progressBar.visibility = View.INVISIBLE
                Toast.makeText(activity,"Please check your internet connection or connect to another network.",
                    Toast.LENGTH_LONG).show()
            }
        })
    }


    fun getMovies(){
        mRecyclerView.layoutManager = GridLayoutManager(activity, numberOfColumns)
        movieAdapter = HomeMovieAdapter(movieDTO)
        if(mRecyclerView.itemDecorationCount > 0)
            mRecyclerView.removeItemDecorationAt(0)
        mRecyclerView.addItemDecoration(HomeCustomDecoration(25))
        mRecyclerView.adapter = movieAdapter
    }

}