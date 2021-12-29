package com.onurberin.movieapp
//https://developers.themoviedb.org/3/getting-started/introduction
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.onurberin.movieapp.data.remote.MovieAPI
import com.onurberin.movieapp.data.remote.MovieDTO
import com.onurberin.movieapp.data.remote.MovieFirstData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private lateinit var v: View
    var BASE_URL: String = "https://api.themoviedb.org/3/"
    var retrofit: Retrofit? = null
    var movieAPI: MovieAPI? = null
    var movieCall: Call<MovieFirstData>? = null
    var movieFirstData: MovieFirstData? = null
    private lateinit var movieDTO: List<MovieDTO>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        movieAPI = retrofit!!.create(MovieAPI::class.java)

        fetchData()

    }

    fun fetchData(){

        movieCall = movieAPI!!.getPopularMovies()

        println("Deneme")

        movieCall!!.enqueue(object : Callback<MovieFirstData> {
            override fun onResponse(
                call: Call<MovieFirstData>,
                response: Response<MovieFirstData>
            ) {
                if (response.body()!=null){
                    movieFirstData = response.body()

                    movieFirstData?.let {
                        movieDTO = it.results
                        println(movieDTO.get(0))
                        println(movieDTO.get(1))
                        println(movieDTO.size)
                    }

                }
            }

            override fun onFailure(call: Call<MovieFirstData>, t: Throwable) {
                println("başarısız")
            }


        })




    }

}