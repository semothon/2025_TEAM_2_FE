package com.example.team2.presentation.participationlist

import androidx.lifecycle.ViewModel
import com.example.team2.R
import com.example.team2.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TransactionViewModel : ViewModel() {

    private val _transactions = MutableStateFlow(
        listOf(
            Transaction(1, "맥도날드", "정건 맥날 같이 시켜드실 분 구합니다.", true,
                listOf(R.drawable.profile_illustration_1, R.drawable.profile_illustration_2, R.drawable.profile_illustration_3, R.drawable.profile_illustration_4)),
            Transaction(2, "찜생찜사", "정건 김치찌개 같이 드실 분~~", false,
                listOf(R.drawable.profile_illustration_1, R.drawable.profile_illustration_3, R.drawable.profile_illustration_2, R.drawable.profile_illustration_3)),
            Transaction(3, "포케올데이", "다이어트 같이 하실 분 ㅎㅎ", true,
                listOf(R.drawable.profile_illustration_2, R.drawable.profile_illustration_3, R.drawable.profile_illustration_1, R.drawable.profile_illustration_3)),
            Transaction(4, "미미카츠", "돈까스 너무 먹고 싶어요ㅠㅠ", false,
                listOf(R.drawable.profile_illustration_1, R.drawable.profile_illustration_2, R.drawable.profile_illustration_3, R.drawable.profile_illustration_3)),
            Transaction(5, "키와마루야지", "라멘 드실 분 있나요.", false,
                listOf(R.drawable.profile_illustration_1, R.drawable.profile_illustration_3, R.drawable.profile_illustration_2, R.drawable.profile_illustration_3))
        )
    )
    val transactions: StateFlow<List<Transaction>> = _transactions

    // 필터 (선택된 필터와 클릭 횟수)
    private val _filter = MutableStateFlow("" to 0)
    val filter: StateFlow<Pair<String, Int>> = _filter

    // 거래 완료 처리
    fun completeTransaction(id: Int) {
        _transactions.value = _transactions.value.map {
            if (it.id == id) it.copy(isOngoing = false) else it
        }
    }

    // 필터 토글 로직
    fun setFilter(selected: String) {
        val (current, count) = _filter.value
        _filter.value = if (current == selected) {
            "" to count + 1 // 같은 필터 두 번 클릭하면 전체 보기로
        } else {
            selected to count + 1
        }
    }
}