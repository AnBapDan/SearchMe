package com.cm.project.android


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cm.project.android.databinding.ActivityUploadBinding
import com.cm.project.android.models.Markers
import com.google.android.material.bottomnavigation.BottomNavigationView


class UploadActivity : AppCompatActivity() {
    private lateinit var binding:ActivityUploadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitButton.setOnClickListener{
            Toast.makeText(applicationContext,"CLICKED", Toast.LENGTH_LONG).show()
            val newMarker = Markers(
                binding.nameField.toString(),
                binding.lat.toString(),
                binding.lon.toString(),
                binding.image.toString()
            )

            newMarker.saveMarker()
            val homeIntent = Intent(this, MainActivity::class.java)
            startActivity(homeIntent)
        }






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


