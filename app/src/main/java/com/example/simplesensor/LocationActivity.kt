package com.example.simplesensor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.gms.location.*

class LocationActivity : AppCompatActivity() {

    private val tvLocation : TextView by lazy { findViewById(R.id.textview_location) }
    private val fusedLocationClient : FusedLocationProviderClient by lazy { LocationServices.getFusedLocationProviderClient(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        fusedLocationClient.lastLocation.addOnSuccessListener {
            if(it != null){
                tvLocation.text = getString(R.string.location_value, it.latitude, it.longitude, it.time)
            }
            else{
                tvLocation.text = getString(R.string.no_available_position)
            }
        }

        val locationCallback = object :  LocationCallback() {
            override fun onLocationResult(locationResult : LocationResult) {
                val location = locationResult.lastLocation
                if(location != null){
                    tvLocation.text = getString(R.string.location_value, location.latitude, location.longitude, location.time)
                }
            }
        }

        fusedLocationClient.requestLocationUpdates(getLocationRequest(), locationCallback, null)


    }

    private fun getLocationRequest() : LocationRequest {
        val locationRequest = LocationRequest()
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        return locationRequest
    }
}