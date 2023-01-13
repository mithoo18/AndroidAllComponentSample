package com.example.androidallcomponentsample

import com.example.androidallcomponentsample.model.CrewResult
import com.example.androidallcomponentsample.model.MovieResult
import com.example.androidallcomponentsample.model.VideoResult
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("popular")
    fun getMovie(
        @Query("api_key") key : String,
        @Query("page") page : Int
    ) : Deferred<MovieResult>

    @GET("{id}/videos")
    fun getVideos(
        @Path("id") id : Int,
        @Query("api_key") apikey : String
    ) : Deferred<VideoResult>

    @GET("{id}/credits}")
    fun getCrew(
        @Path("id") id : Int,
        @Query("api_key") apikey: String
    ) : Deferred<CrewResult>


    companion object Factory {
        fun create() : ApiInterface{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

        return retrofit.create(ApiInterface::class.java)
        }
    }

}