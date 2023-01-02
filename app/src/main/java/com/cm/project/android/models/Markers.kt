package com.cm.project.android.models

import android.util.Log
import com.cm.project.android.config.FirebaseConfig
import com.google.firebase.database.DatabaseReference


data class Markers(var location:String, var lat: String, var lon: String, var image: String){
    fun saveMarker() {
        var database: DatabaseReference? = FirebaseConfig.firebaseDatabase
        database?.child("Markers")?.child(this.location)?.setValue(this)
    }
}
