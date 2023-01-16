package com.example.androidallcomponentsample

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.androidallcomponentsample.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        with(binding){
            setContentView(root)
            val user = UserBuilder("Nishant","Srivastava")
                .age(24)
                .phone("9898767898")
                .address("My Address")
                .build()

            val text = user.firstname + "" + user.lastName

            fab.setOnClickListener { view ->
                Snackbar.make(view,text,Snackbar.LENGTH_LONG).show()
            }
        }


        }
    }

