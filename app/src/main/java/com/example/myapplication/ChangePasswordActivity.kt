package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ChangePasswordActivity : AppCompatActivity() {

    lateinit var changeButton: Button

    lateinit var changePasswordEditText : EditText

    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        init()
    }

    private fun init(){
        changeButton = findViewById(R.id.changeButton)
        changePasswordEditText = findViewById(R.id.changePasswordEditText)
        auth = Firebase.auth
        changePasswordListeners()
    }

    private fun changePasswordListeners() {
        changeButton.setOnClickListener {
            val password = changePasswordEditText.text.toString()

            auth.currentUser?.updatePassword(password)
                ?.addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "password is updated!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}