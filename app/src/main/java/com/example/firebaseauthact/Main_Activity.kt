package com.example.firebaseauthact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Main_Activity : AppCompatActivity() {
    lateinit var email: TextView
    lateinit var uid: TextView
    lateinit var signout: Button
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email=findViewById(R.id.email3)
        uid=findViewById(R.id.uid)
        signout=findViewById(R.id.signout)
        auth = Firebase.auth
        email.text=intent.getStringExtra("email")
        uid.text=intent.getStringExtra("uid")



        signout.setOnClickListener {
            Firebase.auth.signOut()
            val i = Intent(this,Log_In::class.java)
            startActivity(i)
        }
    }
}