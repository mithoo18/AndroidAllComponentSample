package com.example.androidallcomponentsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent = Intent(this@MainActivity,MyIntentService::class.java)
        intent.putExtra("data", "This text is sent via intent to Intent Service");
        startService(intent)
    }
}