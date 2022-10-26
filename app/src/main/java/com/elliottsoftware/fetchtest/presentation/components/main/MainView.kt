package com.elliottsoftware.fetchtest.presentation.components.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.elliottsoftware.fetchtest.R

@Composable
fun MainView(onNavigate:(Int) -> Unit){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
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