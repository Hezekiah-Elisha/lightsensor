package com.techarchlight.lightsensor

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), SensorEventListener {
    private var sensor:Sensor?=null

    private var sensorManager:SensorManager?=null

    private lateinit var tv:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event!!.values[0]>40){
            tv = findViewById(R.id.changeStateTextView)
            var answer: String = "switched on"
            tv.text = answer
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        var switch_off = "switch off"
        sensorManager!!.unregisterListener(this)
        tv.text = switch_off
    }
}
