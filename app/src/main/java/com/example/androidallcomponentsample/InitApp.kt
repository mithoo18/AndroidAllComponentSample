package com.example.androidallcomponentsample

import android.app.Application

class InitApp  : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(app,mainModule))
    }
}