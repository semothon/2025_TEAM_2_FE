package com.example.team2.network

import com.example.team2.network.model.ApiResponse
import com.example.team2.network.model.MakeRoomResponse
import com.example.team2.network.model.RoomListResponse
import com.example.team2.network.model.SignInRequest
import com.example.team2.network.model.SignInResponse
import com.example.team2.network.model.SignUpRequest
import com.example.team2.network.model.VerifyCodeCertRequest
import com.example.team2.network.model.VerifyCodeRequest
import com.example.team2.presentation.roomadd.model.RoomDetail
import com.example.team2.presentation.roomdetail.model.RoomResponseDetail
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

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

    // 방 리스트 요청
    @GET("group/get/home")
    suspend fun getRoomListData(
        @Header("Authorization") authorization: String,
    ): Response<RoomListResponse>

    // 방 만들기
    @POST("group/create")
    suspend fun makeRoom(
        @Header("Authorization") authorization: String,
        @Body requestBody: RoomDetail
    ): Response<MakeRoomResponse>

    @GET("group/get/detail")
    suspend fun getRoomDetail(
        @Query("groupId") groupId: String
    ): Response<RoomResponseDetail>

//    // 사용자 삭제 요청
//    @DELETE("user/delete")
//    fun deleteUser(
//        @Body requestBody: DeleteRequest
//    ): Call<ApiResponse>
}