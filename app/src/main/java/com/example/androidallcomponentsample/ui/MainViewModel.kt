package com.example.androidallcomponentsample.ui

import android.net.NetworkRequest
import android.net.wifi.WifiManager.AddNetworkResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidallcomponentsample.data.Movie
import com.example.androidallcomponentsample.data.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private var _movieResponse = MutableLiveData<NetworkResult<List<Movie>>>()
    val movieResponse: LiveData<NetworkResult<List<Movie>>> = _movieResponse

    init {
        fetchAllMovies()
    }

    private fun fetchAllMovies() {
        viewModelScope.launch {
            mainRepository.getPopularMovies().collect {
                _movieResponse.postValue(it)
            }
        }
    }
}

