package com.example.firebaseauthact.chat

data class Message(
    val text: String = "",
    val senderId: String = "",
    val receiverId :String = "",
    val timestamp: String = ""

)