package com.cm.project.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.cm.project.android.databinding.ActivityLoginBinding
import com.cm.project.android.databinding.ActivityRegisterBinding
import com.cm.project.android.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        user = User("","","","")
        binding.loginButton.setOnClickListener{
            user.email = binding.emailInput.text.toString()
            user.password = binding.pass1.text.toString()
            auth.signInWithEmailAndPassword(user.email, user.password).addOnCompleteListener(this) { task ->
                Log.d("ERRO", task.exception.toString())
                if (task.isSuccessful) {
                    val profile: FirebaseUser? = auth.currentUser
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Erro ao fazer login.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }
}