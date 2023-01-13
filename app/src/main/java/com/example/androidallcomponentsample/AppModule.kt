package com.example.androidallcomponentsample

import android.app.Application

val app = applicationContext {
    provide{ Application() }
}