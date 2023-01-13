package com.example.androidallcomponentsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidallcomponentsample.model.MovieResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.logging.Logger
import com.example.androidallcomponentsample.model.Result

class MainActivity : AppCompatActivity() {

    private lateinit var apiInterface : ApiInterface
    private var nameList = mutableListOf<String>()
    private var castList = mutableListOf<String>()

    companion object{
        var log = Logger.getLogger(MainActivity::class.java.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiInterface = ApiInterface.create()
        GlobalScope.launch(Dispatchers.IO){
            getMovieCrew(getMovies().await().result)
        }

    }

    private fun getMovies() : Deferred<MovieResult> {
        return apiInterface.getMovie(BuildConfig.MOVIE_KEY,1)
    }

    private suspend fun getMovieCrew(movieList: List<Result>) {
        for(result in movieList){
            nameList.add(result.title)
            castList.add(apiInterface.getCrew(result.id,
            BuildConfig.MOVIEW_KEY).await().cast[0].name)

            log.info(apiInterface.getCrew(result.id,
            BuildConfig.MOVIE_KEY
            ).await().cast[0].name)

            log.info(" " + castList.size)
            GlobalScope.launch(Dispatchers.Main) {
                log.info("Sizes " + nameList + " " + castList.size)
                val linearLayoutManager = LinearLayoutManager(applicationContext)
                linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                val movieAdapter = MovieAdapter(nameList,castList)
                with(movie_crew_recycler_view){
                    layoutManager  = linearLayoutManager
                    adapter = movieAdapter
                }
                progress_bar.visibility = View.GONE

            }

        }
    }



}
