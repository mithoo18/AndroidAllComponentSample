package com.example.androidallcomponentsample

import android.app.Application
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class InitApp : Application(), KodeinAware {

    override val kodein : Kodein = Kodein {
        bind<Retrofit>() with singleton {
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }

    override fun onCreate() {
        super.onCreate()
    }

}