package com.example.team2.presentation.roomdetail.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.team2.R
import com.example.team2.presentation.component.CustomText3
import com.example.team2.presentation.roomdetail.model.Member

@Composable
fun MemberItem(member: Member) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.profile_illustration_1),
            contentDescription = member.name,
            modifier = Modifier.size(40.dp)
        )
        Spacer(Modifier.width(8.dp))
        Row(modifier = Modifier.fillMaxWidth(0.3f)) { CustomText3(text = member.name) }
        Text(text = member.department)
    }
}