package com.cm.project.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cm.project.android.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Build the map.
        supportFragmentManager.beginTransaction().replace(binding.container.id, MapsFragment())
            .commit()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_item_explore ->{
                    val profileIntent = Intent(this, MapsActivity::class.java)
                    true
                    startActivity(profileIntent)
                }
                R.id.nav_item_upload -> {
                    val exploreIntent = Intent(this, UploadActivity::class.java)
                    startActivity(exploreIntent)
                }
                R.id.nav_item_profile -> {
                    // Start the ProfileActivity
                    val profileIntent = Intent(this, LoginActivity::class.java)
                    startActivity(profileIntent)
                }
            }
            true
        }
    }
}