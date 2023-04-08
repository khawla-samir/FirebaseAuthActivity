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

class Log_In : AppCompatActivity() {
    lateinit var email : TextView
    lateinit var password : TextView
    lateinit var signup : Button
    lateinit var login : Button
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        email=findViewById(R.id.email2)
        password=findViewById(R.id.password2)
        signup=findViewById(R.id.signUp2)
        login=findViewById(R.id.login2)
        auth = Firebase.auth

        signup.setOnClickListener {
            val i = Intent(this,Main_Activity::class.java)
            startActivity(i)
        }
        login.setOnClickListener {
            if (email.text.isEmpty() || password.text.isEmpty()) {
                Toast.makeText(this, "All fields are requireds", Toast.LENGTH_SHORT).show()
            } else {
                signInAccount(email.text.toString(), password.text.toString())
            }
        }

    }
    fun updateUI(email: String, uid: String) {
        var i = Intent(this, Main_Activity::class.java)
        i.putExtra("email",email)
        i.putExtra("uid",uid)
        startActivity(i)
    }
    private fun signInAccount(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("kh", "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user!!.email.toString(),user!!.uid.toString())
                } else {
                    // If sign in fails, display a message to the user.
                    Log.e("kh", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
    }
    }
