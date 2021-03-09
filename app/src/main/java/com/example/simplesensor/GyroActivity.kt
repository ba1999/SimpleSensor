package com.example.simplesensor

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class GyroActivity : AppCompatActivity() {

    private val tvResValue : TextView by lazy { findViewById(R.id.tvRes) }
    private val tvRangeValue : TextView by lazy { findViewById(R.id.tvRange) }
    private val tvXValue : TextView by lazy { findViewById(R.id.tvX) }
    private val tvYValue : TextView by lazy { findViewById(R.id.tvY) }
    private val tvZValue : TextView by lazy { findViewById(R.id.tvZ) }

    private val sensorManager : SensorManager by lazy { applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager}
    private val gyro : Sensor by lazy { sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gyro)

        tvResValue.text = getString(R.string.resolution_value, gyro.resolution)
        tvRangeValue.text = getString(R.string.meas_range_value, gyro.maximumRange)

        val gyroListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

            }

            override fun onSensorChanged(sensorEvent: SensorEvent) {
                tvXValue.text = sensorEvent.values[0].toString()
                tvYValue.text = sensorEvent.values[1].toString()
                tvZValue.text = sensorEvent.values[2].toString()

                if(sensorEvent.values[2] > 0.5f){
                    window.decorView.setBackgroundColor(Color.RED)
                }
                else if(sensorEvent.values[2] < -0.5f){
                    window.decorView.setBackgroundColor(Color.GREEN)
                }

            }
        }
        sensorManager.registerListener(gyroListener, gyro,
            SensorManager.SENSOR_DELAY_NORMAL)
    }
}