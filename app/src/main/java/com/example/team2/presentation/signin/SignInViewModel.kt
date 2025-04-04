package com.example.team2.presentation.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.team2.network.RetrofitClient
import com.example.team2.network.model.SignInRequest
import com.example.team2.network.model.SignInResponse
import com.example.team2.token
import com.example.team2.userId
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel : ViewModel() {
    private val _isSignIn = MutableStateFlow(false)
    val isSignIn: StateFlow<Boolean> = _isSignIn

    fun signIn(email: String, password: String) {
        val requestBody = SignInRequest(email, password)
        RetrofitClient.apiService.signInRequest(requestBody)
            .enqueue(object : Callback<SignInResponse> {
                override fun onResponse(
                    call: Call<SignInResponse>,
                    response: Response<SignInResponse>
                ) {
                    if (response.isSuccessful) {
                        _isSignIn.value = true
                        token = response.body()?.token.toString()
                        userId = response.body()?.userId.toString()
                        Log.d("testt", token)
//                        responseMessage.value = response.body()?.message ?: "Login Successful"
                    } else {
//                        responseMessage.value = "Error: ${response.message()}"
                    }
                }

                override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
//                    responseMessage.value = "Request failed: ${t.message}"
                }
            })
    }
}