package com.example.androidallcomponentsample

import android.content.ComponentName
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViewsService
import androidx.work.Data
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.multiprocess.RemoteListenableWorker.ARGUMENT_CLASS_NAME
import androidx.work.multiprocess.RemoteListenableWorker.ARGUMENT_PACKAGE_NAME
import androidx.work.multiprocess.RemoteWorkerService

class MainActivity : AppCompatActivity() {

    private val PACKAGE_NAME = "com.example.background.multiprocess"

    private var workManager: WorkManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workManager = WorkManager.getInstance(this@MainActivity)

        findViewById<Button>(R.id.enqueue_remote_worker).setOnClickListener {
            val serviceName = RemoteWorkerService::class.java.name
            val componentName = ComponentName(PACKAGE_NAME, serviceName)
            val oneTimeWorkRequest = buildOneTimeWorkRemoteWorkRequest(
                componentName,
                ExampleRemoteCoroutineWorker::class.java
            )

            workManager?.enqueue(oneTimeWorkRequest)
        }

        findViewById<Button>(R.id.enqueue_remote_worker2).setOnClickListener {
            val serviceName = RemoteWorkerService2::class.java.name
            val componentName = ComponentName(PACKAGE_NAME, serviceName)

            val oneTimeWorkRequest = buildOneTimeWorkRemoteWorkRequest(
                componentName,
                ExampleRemoteListenableWorker::class.java
            )
            workManager?.enqueue(oneTimeWorkRequest)
        }

        findViewById<Button>(R.id.cancel_remote_worker).setOnClickListener {
            workManager?.cancelAllWorkByTag(ExampleRemoteCoroutineWorker::class.java.name)
            workManager?.cancelAllWorkByTag(ExampleRemoteListenableWorker::class.java.name)
        }
    }

    private fun buildOneTimeWorkRemoteWorkRequest(
        componentName: ComponentName
        , listenableWorkerClass: Class<out ListenableWorker>
    ): OneTimeWorkRequest {

        // ARGUMENT_PACKAGE_NAME and ARGUMENT_CLASS_NAME are used to determine the service
        // that a Worker binds to. By specifying these parameters, we can designate the process a
        // Worker runs in.
        val data: Data = Data.Builder()
            .putString(ARGUMENT_PACKAGE_NAME, componentName.packageName)
            .putString(ARGUMENT_CLASS_NAME, componentName.className)
            .build()

        return OneTimeWorkRequest.Builder(listenableWorkerClass)
            .setInputData(data)
            .build()
    }
}