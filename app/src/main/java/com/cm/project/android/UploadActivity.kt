package com.cm.project.android


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cm.project.android.databinding.ActivityUploadBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class UploadActivity : AppCompatActivity() {
    private lateinit var binding:ActivityUploadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_upload)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_item_home -> {
                    // Start the MainActivity
                    val homeIntent = Intent(this, MainActivity::class.java)
                    true
                    startActivity(homeIntent)

                }
                R.id.nav_item_profile -> {
                    // Start the ProfileActivity
                    val profileIntent = Intent(this, LoginActivity::class.java)
                    true
                    startActivity(profileIntent)

                }
                R.id.nav_item_explore ->{
                    val profileIntent = Intent(this, MapsActivity::class.java)
                    true
                    startActivity(profileIntent)
                }
            }
            true
        }


    }

}


