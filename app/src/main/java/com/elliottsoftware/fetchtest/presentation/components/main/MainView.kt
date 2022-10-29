package com.elliottsoftware.fetchtest.presentation.components.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun MainView(onNavigate:(Int) -> Unit){
    val pages = listOf(
        BoardingPage.First,
        BoardingPage.Second
    )
    val pagerState = rememberPagerState()


    Column(modifier = Modifier.fillMaxWidth()) {
        // Display 10 items
        HorizontalPager(
            modifier = Modifier.weight(8f),
            count = 2,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            // Our page content
            PagerScreen(pages[position])

        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f),
            pagerState = pagerState,
        )
        FinishButton(
            modifier = Modifier.weight(1f),
            pagerState = pagerState,
            onNavigate = onNavigate
        )
    }

}
@Composable
fun PagerScreen(boardingPage: BoardingPage) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Pager Image"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = boardingPage.title,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 20.dp),
            text = boardingPage.description,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onNavigate:(Int) -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 1
        ) {
            Button(
                onClick = { onNavigate(R.id.action_mainFragment_to_listFragment) },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White
                )
            ) {
                Text(text = "Get Data")
            }
        }
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