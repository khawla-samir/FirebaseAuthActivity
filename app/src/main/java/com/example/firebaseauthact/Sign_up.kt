package com.example.firebaseauthact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Sign_up : AppCompatActivity() {

    lateinit var email: TextView
    lateinit var password: TextView
    lateinit var signup: Button
    lateinit var login: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        email = findViewById(R.id.email1)
        password = findViewById(R.id.password1)
        signup = findViewById(R.id.signUp1)
        login = findViewById(R.id.login1)
        auth = Firebase.auth

        login.setOnClickListener {
            val i = Intent(this, Log_In::class.java)
            startActivity(i)
        }
        signup.setOnClickListener {
            if (email.text.isEmpty() || password.text.isEmpty()) {
                Toast.makeText(this, "All fields are requireds", Toast.LENGTH_SHORT).show()
            } else {
                createNewAccount(email.text.toString(), password.text.toString())
            }
        }
    }

    fun updateUI() {
        var i = Intent(this, Log_In::class.java)
        startActivity(i)
    }

    private fun createNewAccount(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("kh", "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("kh", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.", Toast.LENGTH_SHORT
                    ).show()

                }
            }
    }


}


