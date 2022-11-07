package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity() {

    lateinit var registrationEmailEditText : EditText
    lateinit var registrationPasswordEditText : EditText
    lateinit var registrationButton : Button

    private lateinit var auth: FirebaseAuth

//    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        init()

    }

    private fun init(){

        registrationEmailEditText = findViewById(R.id.registrationEmailEditText)
        registrationPasswordEditText = findViewById(R.id.registrationPasswordEditText)
        registrationButton = findViewById(R.id.registrationButton)
        auth = Firebase.auth


        registrationListeners()
    }

    private fun registrationListeners() {
        registrationButton.setOnClickListener {
            val email = registrationEmailEditText.text.toString()
            val password = registrationPasswordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "error!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                    }
                }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task->
                if (task.isSuccessful){
                    startActivity(Intent(this,LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "error!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}