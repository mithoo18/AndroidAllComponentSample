package com.example.androidallcomponentsample.ui

import com.example.androidallcomponentsample.data.ApiService
import javax.inject.Inject
import com.example.androidallcomponentsample.data.NetworkResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getPopularMovies()  = flow {
        emit(NetworkResult.Loading(true))
        val response = apiService.getMostPopularMovies()
        emit(NetworkResult.Success(response.items))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }
}