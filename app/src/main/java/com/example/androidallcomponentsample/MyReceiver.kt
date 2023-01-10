package com.example.androidallcomponentsample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.webkit.ConsoleMessage
import android.widget.Toast
import java.time.Duration

class MyReceiver : BroadcastReceiver() {

 private val action : String = "github.amanjeetsingh150.MY_ACTION"

    override fun onReceive(context: Context?, intent: Intent?) {
        val intentName = intent?.action

        if(intentName == action){
            val s = intent.extras?.getString("data")
            context?.toast("Broadcast Received!! {$s}")
        }
    }

    private fun Context.toast(message: CharSequence,duration: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this,message,duration).show()
    }
}