package com.example.simplesensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private val listviewSensor : ListView by lazy{ findViewById(R.id.listview_sensor) }

    private lateinit var sensorList : List<Sensor>
    private lateinit var sensorManager: SensorManager
    private lateinit var mAdapter : ArrayAdapter<Sensor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)
        mAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, sensorList)

        listviewSensor.adapter = mAdapter

    }
}