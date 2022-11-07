package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    lateinit var profileChangePasswordButton : Button
    lateinit var profileLogoutButton : Button

    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        init()
    }

    private fun init(){
        profileChangePasswordButton = findViewById(R.id.profileChangePasswordButton)
        profileLogoutButton = findViewById(R.id.profileLogoutButton)

        profileListeners()
    }

    private fun profileListeners() {
        profileChangePasswordButton.setOnClickListener {
            startActivity(Intent(this, ChangePasswordActivity::class.java))
        }

        profileLogoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
//            firebaseAuth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}