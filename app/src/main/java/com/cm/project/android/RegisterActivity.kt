package com.cm.project.android

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cm.project.android.databinding.ActivityRegisterBinding
import com.cm.project.android.models.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.regex.Pattern


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.jumpLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.registerButton.setOnClickListener{
            val username: Pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE)
            if(username.matcher(binding.userInput.text).find()){
                Toast.makeText(applicationContext,getString(R.string.invalid_username), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.emailInput.text).matches() || TextUtils.isEmpty(binding.emailInput.text)){
                Toast.makeText(applicationContext,getString(R.string.invalid_email), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (binding.pass1.text.toString() != binding.pass2.text.toString()){
                Toast.makeText(applicationContext,getString(R.string.password_mismatch), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            //Register on Firebase
            auth = FirebaseAuth.getInstance()
            val newUser = User(binding.userInput.text.toString(), binding.emailInput.text.toString(),binding.pass1.text.toString(),"-1")
            auth.createUserWithEmailAndPassword(newUser.email, newUser.password)
                .addOnCompleteListener(this,
                    OnCompleteListener<AuthResult?> { task ->
                        if (task.isSuccessful) {
                            val user: FirebaseUser? = auth.currentUser
                            if (user != null) {
                                newUser.uid = user.uid
                            }
                            newUser.saveData()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Erro ao criar um login.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })

        }
    }

}