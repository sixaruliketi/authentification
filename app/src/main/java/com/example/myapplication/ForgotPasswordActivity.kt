package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    lateinit var forgotPasswordEmailEditText : EditText
    lateinit var forgotPasswordSendButton : Button

    val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        init()
    }

    private fun init(){
        forgotPasswordEmailEditText = findViewById(R.id.forgotPasswordEmailEditText)
        forgotPasswordSendButton = findViewById(R.id.forgotPasswordSend)

        forgotPasswordListeners()

    }

    private fun forgotPasswordListeners() {
        forgotPasswordSendButton.setOnClickListener {
            val email = forgotPasswordEmailEditText.text.toString()

            if (email.isEmpty()){
                Toast.makeText(this, "email is empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "check email!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "error!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}