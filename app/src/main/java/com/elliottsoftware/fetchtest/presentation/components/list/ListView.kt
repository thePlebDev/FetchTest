package com.elliottsoftware.fetchtest.presentation.components.list

import android.widget.ListView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.elliottsoftware.fetchtest.presentation.components.main.NavButton

@Composable
fun ListView(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        MessageList()

    }

}

val calfList = listOf<String>("fdaf","fdsaf","fdsafdtre","another","moreTest","Another one")

@Composable
fun MessageList() {
    LazyColumn {
        items(calfList) { tagNumber ->
            Card(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .fillMaxWidth(),
                elevation = 2.dp,
                backgroundColor = Color.White,
                shape = RoundedCornerShape(corner = CornerSize(16.dp))
            ){
                Row(
                    modifier = Modifier.padding(24.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Column(modifier = Modifier.weight(2f)){

                        Text(tagNumber,style= MaterialTheme.typography.h6, textAlign = TextAlign.Start)
                        Text("Legs are a little weak",style= MaterialTheme.typography.subtitle1)
                    }
                    Column(modifier = Modifier.weight(1f)){

                        Text("2022-10-23",style= MaterialTheme.typography.subtitle1)
                        Text("Bull",style= MaterialTheme.typography.subtitle1)
                    }

                }


            }
        }
    }
}