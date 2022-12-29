package com.cm.project.android.config

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object FirebaseConfig {
    val firebaseDatabase: DatabaseReference = FirebaseDatabase.getInstance().reference
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
}