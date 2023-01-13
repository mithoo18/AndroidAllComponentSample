package com.example.androidallcomponentsample

import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Observable
import com.example.androidallcomponentsample.model.MovieResult

interface ApiInterface{

    @GET("popular")
    fun getMovies(@Query("api_key") key: String,
                  @Query("page") page: Int): Observable<MovieResult>
}