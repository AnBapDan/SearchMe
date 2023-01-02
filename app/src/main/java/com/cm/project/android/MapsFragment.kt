package com.cm.project.android

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlin.math.*

class MapsFragment : Fragment(), GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener {


    lateinit var client: FusedLocationProviderClient

    private var locationCallback: LocationCallback = object: LocationCallback(){
        override fun onLocationResult(p0: LocationResult) {
            super.onLocationResult(p0)
            val location1: Location = p0.lastLocation
            onLocationChange(location1)
        }
    }

    private var locationRequest: LocationRequest = LocationRequest()
        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        .setInterval(10000)
        .setFastestInterval(1000)

    private var mCurrLocationMarker: Marker? = null
    private var mMap: GoogleMap? = null

    private val callback = OnMapReadyCallback { googleMap ->
        mMap = googleMap
        val aveiro = LatLng(40.63290338497802, -8.65919152474748)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(aveiro))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_maps, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onStart() {
        super.onStart()
        client = LocationServices.getFusedLocationProviderClient(activity as MainActivity)

        if (checkSelfPermission(
                activity as MainActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            checkSelfPermission(
                activity as MainActivity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startLocationUpdates()
        } else {
            ActivityCompat.requestPermissions(
                activity as MainActivity,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), 100
            )
        }
    }

    override fun onStop() {
        super.onStop()
        stopLocationUpdates()
    }

    fun onLocationChange(location: Location){
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker!!.remove()
        }
        val latLng = LatLng(location.latitude, location.longitude)
        val markerOptions = MarkerOptions()
        markerOptions.position(latLng)
        markerOptions.title("Current Position")

        mCurrLocationMarker = mMap!!.addMarker(markerOptions)
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap!!.animateCamera(CameraUpdateFactory.zoomTo(18f))

    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        client.requestLocationUpdates(locationRequest,
            locationCallback,
            Looper.getMainLooper())
    }

    private fun stopLocationUpdates() {
        client.removeLocationUpdates(locationCallback)
    }

    override fun onMyLocationButtonClick(): Boolean {
        return false
    }


    override fun onMyLocationClick(p0: Location) {}

}