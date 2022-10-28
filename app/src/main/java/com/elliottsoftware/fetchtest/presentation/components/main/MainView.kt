package com.elliottsoftware.fetchtest.presentation.components.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elliottsoftware.fetchtest.R
import com.elliottsoftware.fetchtest.presentation.components.list.LoadingAnimation

@Composable
fun MainView(onNavigate:(Int) -> Unit){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Reasons to hire Tristan Elliott",fontSize = 22.sp,
            fontWeight = FontWeight.Bold,textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 0.dp,16.dp,0.dp,0.dp)
        )
        Text("1) Tristan is a great communicator ",fontSize = 18.sp, fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,)
        NavButton(onNavigate)
    }

}

@Composable
fun NavButton(onNavigate:(Int) -> Unit) {
    Button(onClick = {
        onNavigate(R.id.action_mainFragment_to_listFragment)
    }) {
        Text(text = "List Fragment")
    }
}