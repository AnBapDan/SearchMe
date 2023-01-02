package com.cm.project.android.models

import android.util.Log
import com.cm.project.android.config.FirebaseConfig
import com.google.firebase.database.DatabaseReference

fun loadMarkers(){
    var database: DatabaseReference? = FirebaseConfig.firebaseDatabase
database?.child("Markers")?.get()
}

val markers: List<Markers> = emptyList()
data class Markers(var location:String, var lat: String, var lon: String, var description: String){
    fun saveMarker() {
        var database: DatabaseReference? = FirebaseConfig.firebaseDatabase
        Log.d("Marker", this.toString())
        database?.child("Markers")?.child(this.location)?.setValue(this)
    }
}
