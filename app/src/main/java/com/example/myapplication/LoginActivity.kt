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

class LoginActivity : AppCompatActivity() {

    lateinit var loginEmailEditText : EditText
    lateinit var loginPasswordEditText : EditText
    lateinit var loginButton : Button
    lateinit var loginRegistrationButton : Button
    lateinit var loginForgotPasswordButton : Button

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()

    }

    private fun init(){
        loginEmailEditText = findViewById(R.id.LoginEmailEditText)
        loginPasswordEditText = findViewById(R.id.LoginPasswordEditText)
        loginButton = findViewById(R.id.LoginButton)
        loginRegistrationButton = findViewById(R.id.LoginRegistrationButton)
        loginForgotPasswordButton = findViewById(R.id.LoginForgotPasswordButton)

        auth = Firebase.auth

        loginListeners()

    }

    private fun loginListeners(){
        loginButton.setOnClickListener {

            val email : String = loginEmailEditText.text.toString()
            val password : String = loginPasswordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "email or password is empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "error!", Toast.LENGTH_SHORT).show()
                }
            }

        }

        loginRegistrationButton.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        loginForgotPasswordButton.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
    }
}