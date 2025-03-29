package com.example.team2.presentation.addroom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.team2.presentation.addroom.model.AddRoom

@Composable
fun AddRoomScreen(navController: NavController, viewModel: AddRoomViewModel = AddRoomViewModel()) {
    var roomTitle by remember { mutableStateOf("") }
    var roomContent by remember { mutableStateOf("") }
    var selectedTogether by remember { mutableStateOf<Boolean?>(null) }
    var roomTotalPeopleCount by remember { mutableIntStateOf(0) }
    var selectedGender by remember { mutableStateOf("") }
    var roomLocation by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = roomTitle,
            onValueChange = { roomTitle = it },
            placeholder = { Text("방 제목") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = roomContent,
            onValueChange = { roomContent = it },
            placeholder = { Text("상세 설명") },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.2f),
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("같이 여유")
            RadioButton(
                selected = selectedTogether == true,
                onClick = { selectedTogether = true }
            )
            Text("O")
            RadioButton(
                selected = selectedTogether == false,
                onClick = { selectedTogether = false }
            )
            Text("X")
        }

        OutlinedTextField(
            value = roomTotalPeopleCount.toString(),
            onValueChange = { roomTotalPeopleCount = it.toIntOrNull() ?: 0 },
            placeholder = { Text("전체 인원") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("성별 선택")
            RadioButton(
                selected = selectedGender == "동성",
                onClick = { selectedGender = "동성" }
            )
            Text("동성")
            RadioButton(
                selected = selectedGender == "이성",
                onClick = { selectedGender = "이성" }
            )
            Text("이성")
        }

        OutlinedTextField(
            value = roomLocation,
            onValueChange = { roomLocation = it },
            label = { Text("위치") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.weight(1f))
        Button(
            onClick = {
                viewModel.makeRoom(
                    AddRoom(
                        roomTitle,
                        roomContent,
                        selectedTogether ?: true,
                        roomTotalPeopleCount,
                        selectedGender,
                        roomLocation
                    )
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("생성하기")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddRoomPreview() {
    AddRoomScreen(rememberNavController())
}