package com.example.androidallcomponentsample
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(),SensorEventListener {
    private var mFace: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mFace = findViewById<TextView>(R.id.faceTextView)
        var sensorManager : SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        var SensorList : List<android.hardware.Sensor> = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(accelerometerListener,sensorList.get(0),0,null);
    }

    override fun onSensorChanged(event: SensorEvent?) {
        var z = event!!.values[2]
        if(z > 9 && z < 10){
            mFace!!.text = "FACE UP"
        }
        else if( z > -10 && z < -9){
            mFace!!.text = "FACE DOWN"
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }


}