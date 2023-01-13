package com.example.androidallcomponentsample

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.work.WorkerParameters
import androidx.work.multiprocess.RemoteCoroutineWorker

class ExampleRemoteCoroutineWorker(context: Context, parameters: WorkerParameters) :
    RemoteCoroutineWorker(context, parameters) {

    override suspend fun doRemoteWork(): Result {

        Log.d(TAG, "Starting ExampleRemoteCoroutineWorker")

        // Do some work here

        return Result.success()
    }

    companion object {
        private const val TAG = "CoroutineWorker"
    }
}