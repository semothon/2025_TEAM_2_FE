package com.example.team2.presentation.participationlist.model

import com.google.gson.annotations.SerializedName

data class ParticipationListResponse(
    @SerializedName("message") val title: String,
    @SerializedName("groups") val roomDerail: List<ParticipationRoom>
)