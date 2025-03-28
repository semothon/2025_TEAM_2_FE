package com.example.team2.presentation.signup.model

data class SignUp(
    val email: String,
    val verificationCode: String,
    val school: String,
    val department: String,
    val studentId: String,
    val gender: String,
    val nickname: String,
    val phone: String,
    val confirmPhone: String
)