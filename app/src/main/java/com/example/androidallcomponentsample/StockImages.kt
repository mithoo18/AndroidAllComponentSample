package com.example.androidallcomponentsample

import android.net.Uri
import java.util.*

internal object StockImages {
    private val random = Random()
    private val imageUris = arrayOf(
        Uri.parse("file:///android_asset/images/lit_pier.jpg"),
        Uri.parse("file:///android_asset/images/parting_ways.jpg"),
        Uri.parse("file:///android_asset/images/wrong_way.jpg"),
        Uri.parse("file:///android_asset/images/jack_beach.jpg"),
        Uri.parse("file:///android_asset/images/jack_blur.jpg"),
        Uri.parse("file:///android_asset/images/jack_road.jpg")
    )

   fun randomStockImage() : Uri {
       val index = random.nextInt(imageUris.size)
       return imageUris[index]
   }
}