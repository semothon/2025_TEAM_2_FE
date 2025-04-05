package com.example.team2.presentation.roomadd

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.team2.network.RetrofitClient
import com.example.team2.presentation.roomadd.model.RoomDetail
import com.example.team2.token
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RoomAddViewModel : ViewModel() {
    private val _foodOptions = MutableStateFlow(
        listOf(
            "한식",
            "중식",
            "양식",
            "일식",
            "패스트푸드",
            "분식",
            "디저트",
            "치킨",
            "피자"
        )
    )
    val foodOptions: MutableStateFlow<List<String>> = _foodOptions

    private val _keywordList = MutableStateFlow(
        listOf(
            "맵찔이",
            "비흡연자만",
            "고단백",
            "비건"
        )
    )
    val keywordList: StateFlow<List<String>> = _keywordList

    private val _memberCountOptions = MutableStateFlow((2..10).map { it.toString() })
    val memberCountOptions: MutableStateFlow<List<String>> = _memberCountOptions

    private val _selectedKeywords = MutableStateFlow(listOf<String>())
    val selectedKeywords: StateFlow<List<String>> = _selectedKeywords

    private val _popBack = MutableStateFlow(false)
    val popBack: MutableStateFlow<Boolean> = _popBack

    fun makeRoom(newRoom: RoomDetail) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.makeRoom("Bearer $token", newRoom)

                if (response.isSuccessful) {
                    _popBack.value = true
                    Log.d("testt", response.body().toString())
                } else {
                    // 실패 시 처리
                    Log.d("testt", "Error: ${response.message()}")
                }
            } catch (e: Exception) {
                // 요청 실패 시 처리
                Log.d("testt", "Request failed: ${e.message}")
            }
        }
    }

    fun editKeyword(keyword: String) {
        if (_selectedKeywords.value.contains(keyword))
            _selectedKeywords.value = _selectedKeywords.value.filter { it != keyword }
        else
            _selectedKeywords.value += keyword
    }
}