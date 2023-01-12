package com.example.androidallcomponentsample

import android.app.Application
import android.media.VolumeShaper.Operation
import android.provider.SyncStateContract.Constants
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkManager

class FilterViewModel(application: Application) : ViewModel() {

private val workManager = WorkManager.getInstance(application)

    internal val workInfo = workManager.getWorkInfoByIdLiveData(Constants.TAG_OUTPUT)

    internal fun apply(imageOperation: ImageOperations){
        imageOperation.continuation.enqueue()
    }

    internal fun cancel(){
        workManager.cancelUniqueWork(Constants.IMAGE_MANIPULATION_WORK_NAME)
    }

}

class FilterViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(FilterViewModel::class.java)) {
            FilterViewModel(application) as T
        }
        else{
            throw java.lang.IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}
