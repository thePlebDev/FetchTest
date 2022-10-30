package com.elliottsoftware.fetchtest.presentation.components.list

import android.util.Log
import android.widget.ListView
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.elliottsoftware.fetchtest.data.models.FetchItem
import androidx.paging.compose.items
import com.elliottsoftware.fetchtest.domain.models.UIResponse
import com.elliottsoftware.fetchtest.presentation.components.main.NavButton
import com.elliottsoftware.fetchtest.presentation.viewModels.ListViewModel

@Composable
fun ListView(viewModel: ListViewModel){

    Column(horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Center) {
        when(val response = viewModel.state.value.listData){
            is UIResponse.Loading ->   LoadingAnimation()
            is UIResponse.Success ->{
                MoreList(response.data)

            }
            is UIResponse.Failure ->{
                Fail(viewModel)
            }
        }

       // MessageList()


    }

}

@Composable
fun Fail(viewModel: ListViewModel){
    val state = rememberDraggableState(

        onDelta = {}
    )
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .draggable(
            orientation = Orientation.Vertical,
            state = state,
            onDragStopped = {viewModel.getList() }
        ),
        horizontalAlignment = Alignment.CenterHorizontally,


    ) {
        Text(text = "Error! Please check internet connection")
        Text(text = "Pull down to refresh")
        Icon(Icons.Rounded.Refresh,
            contentDescription = "Localized description",
            tint = Color(red = 255, green = 169, blue = 0),
            modifier = Modifier.size(128.dp)
        )

    }
}
@Composable
fun MoreList(list:List<FetchItem>){
    LazyColumn{
        items(list) { index ->
            FetchItemRow(index)
        }
    }


}

//@Composable
//fun MessageList(viewModel: ListViewModel = viewModel()) {
//    val fetchItems = viewModel.items.collectAsLazyPagingItems()
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(
//                horizontal = 16.dp,
//                vertical = 32.dp
//            ),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        when (val state = fetchItems.loadState.prepend) {
//            is LoadState.NotLoading -> Unit
//            is LoadState.Loading -> {
//                Log.d("refresh","PREPEND")
//                Loading()
//            }
//            is LoadState.Error -> {
//                Log.e("Error prepend","Error prepend")
//            }
//        }
//        when (val state = fetchItems.loadState.refresh) {
//            is LoadState.NotLoading -> Unit
//            is LoadState.Loading -> {
//                Log.d("refresh","refresh")
//                Loading()
//            }
//            is LoadState.Error -> {
//                Log.e("Error refresh","Error refresh")
//            }
//        }
//        items(
//            items = fetchItems,
//            key = { it.id }
//        ) {
//            FetchItemRow(item = it)
//        }
//
//    }
//}

private fun LazyListScope.Loading() {
    item {
        LinearProgressIndicator()
    }
}

@Composable
fun FetchItemRow(item:FetchItem?){
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color(red = 255, green = 169, blue = 0),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ){
        Row(
            modifier = Modifier.padding(24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Column(modifier = Modifier.weight(2f)){

                Text("name:"+item?.name,style= MaterialTheme.typography.h6, textAlign = TextAlign.Start, color = Color(red=48, green = 13,blue=56))
                Text("id:"+ item?.id,style= MaterialTheme.typography.subtitle1)
            }
            Column(modifier = Modifier.weight(1f)){

                Text("listId"+ item?.listId,style= MaterialTheme.typography.subtitle1,color = Color(red=48, green = 13,blue=56))

            }

        }


    }
}