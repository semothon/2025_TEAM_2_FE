package com.example.team2.network

import com.example.team2.network.model.ApiResponse
import com.example.team2.network.model.SignInRequest
import com.example.team2.network.model.SignInResponse
import com.example.team2.network.model.SignUpRequest
import com.example.team2.network.model.VerifyCodeCertRequest
import com.example.team2.network.model.VerifyCodeRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/univ-cert-request")
    fun verifyCodeRequest(
        @Body requestBody: VerifyCodeRequest
    ): Call<ApiResponse>

    // 대학 인증 코드 확인
    @POST("auth/univ-cert-verify")
    fun verifyCodeCertRequest(
        @Body requestBody: VerifyCodeCertRequest
    ): Call<ApiResponse>

    // 회원가입 요청
    @POST("auth/register")
    fun signUpRequest(
        @Body requestBody: SignUpRequest
    ): Call<ApiResponse>

    // 로그인 요청
    @POST("auth/login")
    fun signInRequest(
        @Body requestBody: SignInRequest
    ): Call<SignInResponse>
//
//    // 사용자 삭제 요청
//    @DELETE("user/delete")
//    fun deleteUser(
//        @Body requestBody: DeleteRequest
//    ): Call<ApiResponse>
}