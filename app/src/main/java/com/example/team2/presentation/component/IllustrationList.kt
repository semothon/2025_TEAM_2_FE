package com.example.team2.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.team2.R

@Composable
fun IllustrationGrid(illustrations: List<Int>) {
    val illustrationsList = illustrations + R.drawable.ic_plus
    Column(modifier = Modifier.size(62.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
            illustrationsList.getOrNull(0)?.let { illustration ->
                Image(
                    painter = painterResource(illustration),
                    contentDescription = illustration.toString(),
                    modifier = Modifier.size(30.dp)
                )
            }
            illustrationsList.getOrNull(1)?.let { illustration ->
                Image(
                    painter = painterResource(illustration),
                    contentDescription = illustration.toString(),
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
            illustrationsList.getOrNull(2)?.let { illustration ->
                Image(
                    painter = painterResource(illustration),
                    contentDescription = illustration.toString(),
                    modifier = Modifier.size(30.dp)
                )
            }
            illustrationsList.getOrNull(3)?.let { illustration ->
                Image(
                    painter = painterResource(illustration),
                    contentDescription = illustration.toString(),
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}