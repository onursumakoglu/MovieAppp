package com.onurberin.movieapp.view
//https://developers.themoviedb.org/3/getting-started/introduction
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onurberin.movieapp.adapter.MovieAdapter
import com.onurberin.movieapp.R
import com.onurberin.movieapp.data.remote.MovieAPI
import com.onurberin.movieapp.data.remote.MovieDTO
import com.onurberin.movieapp.data.remote.MovieFirstData
import com.onurberin.movieapp.data.remote.RetroInstance
import retrofit2.Response
import retrofit2.Retrofit


class HomeFragment : Fragment() {

    private val BASE_URL: String = "https://api.themoviedb.org/3/"
    private var retrofit: Retrofit? = null
    private var movieAPI: MovieAPI? = null
    private var movieCall: retrofit2.Call<MovieFirstData>? = null
    private var movieFirstData: MovieFirstData? = null
    private lateinit var movieDTO: List<MovieDTO>
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    var numberOfColumns = 2
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.loading_progress)
        progressBar.visibility = View.VISIBLE
        mRecyclerView = view.findViewById(R.id.home_recycler_view)

        retrofit = RetroInstance.getRetroInstance()
        movieAPI = retrofit!!.create(MovieAPI::class.java)
        fetchData()
    }


    private fun fetchData(){
        movieCall = movieAPI!!.getPopularMovies()
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
        movieAdapter = MovieAdapter(movieDTO)
        if(mRecyclerView.itemDecorationCount > 0)
            mRecyclerView.removeItemDecorationAt(0)
        mRecyclerView.addItemDecoration(HomeCustomDecoration(25))
        mRecyclerView.adapter = movieAdapter

    }

    /*private fun attachPopularMoviesOnScrollListener(){
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            }
        })
    }
     */
}