package com.example.androidallcomponentsample

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray


class ListActivity : AppCompatActivity() {

    final var url = "https://androidcookbook.com/seam/resource/rest/recipe/list";
    final var TAG = "VolleyDemoKotlin.ListActivity"
    var listView : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.listview) as RecyclerView

        if(isNetworkConnected()){
            getRecipies()
        }
    }

    private fun getRecipies() {
        Log.d(TAG, "ListActivity.getRecipes()")
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
        return false
    }

    private fun process(response : JSONArray){
        Log.d(TAG,"ListActivity.process()")
        for(i in 1..response.length()){
            Log.d(TAG,"${response.get(i)}")
        }

    }

}