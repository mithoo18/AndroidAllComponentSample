package com.example.androidallcomponentsample

val MainModule = applicationContext {
    provide { MainPresenter() }
}