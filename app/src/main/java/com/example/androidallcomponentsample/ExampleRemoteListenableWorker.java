package com.example.androidallcomponentsample;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.work.WorkerParameters;
import androidx.work.multiprocess.RemoteListenableWorker;

import com.google.common.util.concurrent.ListenableFuture;

import javax.security.auth.callback.Callback;

public class ExampleRemoteListenableWorker extends RemoteListenableWorker {
    /**
     * @param appContext   The application {@link Context}
     * @param workerParams {@link WorkerParameters} to setup the internal state of this worker
     */

    private static final String TAG = "ListenableWorker";


    public ExampleRemoteListenableWorker(@NonNull Context appContext, @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    @NonNull
    @Override
    public ListenableFuture<Result> startRemoteWork() {
        return CallbackToFutureAdapter.getFuture(completer -> {
            Log.i(TAG, "Starting ExampleRemoteListenableWorker");
            return completer.set(Result.success());
        });
    }
}
