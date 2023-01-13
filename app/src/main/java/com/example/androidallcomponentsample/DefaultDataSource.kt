package com.example.androidallcomponentsample

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import androidx.lifecycle.liveData


class DefaultDataSource(
    private val ioDispatcher : CoroutineDispatcher
) : DataSource {

    override fun getCurrentTime() : LiveData<Long> =
        liveData {
            with(true){
                emit(System.currentTimeMillis())
                delay(1000)
            }
        }

    private val weatherCondition = listOf("Sunny","Cloudy","Rainy","Storm","Snowy")

    override fun fetchWeather() : LiveData<String> = liveData {
        var counter = 0
        while (true){
            counter++
            delay(2000)

            emit(weatherCondition[counter % weatherCondition.size])
        }
    }


    private val _cachedData = MutableLiveData("This is old data")
    override val cachedData : LiveData<String> = _cachedData

    override suspend fun fetchNewData(){
        withContext(Dispatchers.Main){
            _cachedData.value = "Fetching new Data..."
            _cachedData.value = simulateNetworkDataFetch()

        }
    }

    private var counter = 0

    private suspend fun simulateNetworkDataFetch() : String = withContext(ioDispatcher){
        delay(3000)
        counter++
        "New Data from request #$counter"
    }

}


interface DataSource {
    fun getCurrentTime() : LiveData<Long>
    fun fetchWeather() : LiveData<String>
    val cachedData : LiveData<String>
    suspend fun fetchNewData()
}