package com.example.team2.network

import com.example.team2.network.model.ApiResponse
import com.example.team2.network.model.BlockUserIdRequest
import com.example.team2.network.model.MakeRoomResponse
import com.example.team2.network.model.RoomListResponse
import com.example.team2.network.model.RoomResponseDetail
import com.example.team2.network.model.SearchQuery
import com.example.team2.network.model.SignInRequest
import com.example.team2.network.model.SignInResponse
import com.example.team2.network.model.SignUpRequest
import com.example.team2.network.model.UpdateRoomRequest
import com.example.team2.network.model.UserDetailResponse
import com.example.team2.network.model.VerifyCodeCertRequest
import com.example.team2.network.model.VerifyCodeRequest
import com.example.team2.presentation.participationlist.model.ParticipationListResponse
import com.example.team2.presentation.roomadd.model.RoomDetail
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
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

    // 회원 가입 요청
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

    // 방 상세 정보
    @GET("group/get/detail")
    suspend fun getRoomDetail(
        @Query("groupId") requestBody: String
    ): Response<RoomResponseDetail>

    // 방 거래 내역
    @GET("group/get/deals")
    suspend fun getRoomDeals(
        @Header("Authorization") authorization: String,
    ): Response<ParticipationListResponse>

    // 방 검색
    @GET("group/search")
    suspend fun getSearchedRoomList(
        @Header("Authorization") authorization: String,
        @Query("query") requestBody: String
    ): Response<RoomListResponse>

    // 방 참여 하기
    @POST("group/join")
    suspend fun joinRoom(
        @Header("Authorization") authorization: String,
        @Body requestBody: SearchQuery
    ): Response<ApiResponse>

    // 유정 정보
    @GET("user/profile/detail")
    suspend fun getUserDetail(
        @Query("userId") requestBody: String
    ): Response<UserDetailResponse>

    // 유저 차단
    @POST("user/block")
    suspend fun blockUser(
        @Header("Authorization") authorization: String,
        @Body requestBody: BlockUserIdRequest
    ): Response<ApiResponse>

    // 방 상태 변경
    @PATCH("group/update")
    suspend fun updateRoom(
        @Header("Authorization") authToken: String,
        @Body requestBody: UpdateRoomRequest
    ): Response<ApiResponse>
}