package com.elliottsoftware.fetchtest.presentation.components.list

import android.widget.ListView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.elliottsoftware.fetchtest.data.models.FetchItem
import com.elliottsoftware.fetchtest.domain.models.UIResponse
import com.elliottsoftware.fetchtest.presentation.components.main.NavButton
import com.elliottsoftware.fetchtest.presentation.viewModels.ListViewModel

@Composable
fun ListView(viewModel: ListViewModel = viewModel()){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        when(val response = viewModel.state.value.listData){
            is UIResponse.Loading -> CircularProgressIndicator()
            is UIResponse.Success ->{
                MessageList(response.data)

            }
            is UIResponse.Failure ->{
                Text(text = "FAIL")
            }
        }



    }

}

@Composable
fun MessageList(list:List<FetchItem>) {
    LazyColumn {
        items(list) { item ->
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

                        Text("name:"+item.name,style= MaterialTheme.typography.h6, textAlign = TextAlign.Start)
                        Text("id:"+ item.id,style= MaterialTheme.typography.subtitle1)
                    }
                    Column(modifier = Modifier.weight(1f)){

                        Text("listId"+ item.listId,style= MaterialTheme.typography.subtitle1)

                    }

                }


            }
        }
    }
}