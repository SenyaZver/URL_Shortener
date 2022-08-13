package com.example.urlshortener.presentation.history_screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HistoryScreen(
    viewModel: HistoryScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, top = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HistoryTitle(Modifier)

        Spacer(Modifier.height(20.dp))

        LazyColumn {
            items(state.URL_list.size) { index ->
                URLcard(state.URL_list[index])
            }
        }


    }

}


@Composable
fun HistoryTitle(modifier: Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(0.1f)
            .padding(5.dp),
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(width = 5.dp, color = Color.Black),
        elevation = 10.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = MaterialTheme.colors.secondary, fontSize = 25.sp)) {
                        append("H")
                    }

                    withStyle(SpanStyle(color = MaterialTheme.colors.secondary, fontSize = 25.sp)) {
                        append("istory")
                    }
                },
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.Center)

            )
        }
    }
}