package com.example.team2.presentation.signup.model

data class SignUp(
    val email: String,
    val verificationCode: String,
    val password: String,
    val confirmPassword: String
)