package com.example.androidallcomponentsample

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button_broadcast : Button = findViewById<Button>(R.id.button_broadcast)

        button_broadcast.setOnClickListener{
            val intent =  Intent("github.amanjeetsingh150.MY_ACTION")
            intent.putExtra("data","Broadcast Message!!")
            sendBroadcast(intent)
        }
    }
}