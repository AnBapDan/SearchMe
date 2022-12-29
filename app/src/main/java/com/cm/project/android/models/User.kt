package com.cm.project.android.models

import android.util.Log
import com.cm.project.android.config.FirebaseConfig
import com.google.firebase.database.DatabaseReference

data class User(var username:String, var email: String, var password: String, var uid: String) {
    fun saveData() {
        var database: DatabaseReference? = FirebaseConfig.firebaseDatabase
        Log.d("USER", this.toString())
        database?.child("Users")?.child(this.uid)?.setValue(this)
    }
}
