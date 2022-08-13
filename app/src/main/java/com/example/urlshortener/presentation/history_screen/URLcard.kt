package com.example.urlshortener.presentation.history_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.urlshortener.data.model.URL
import com.example.urlshortener.presentation.theme.URLShortenerTheme

@Composable
fun URLcard(url: URL, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(0.9f)
            .height(80.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 2.dp,
        border = BorderStroke(2.dp, color = MaterialTheme.colors.secondary),
    ) {


        Column(
            modifier = modifier.padding(10.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            val scroll = rememberScrollState(0)
            SelectionContainer {
                Text(
                    text = url.address,
                    Modifier.horizontalScroll(scroll)
                )
            }

            Spacer(
                modifier = Modifier.height(10.dp),
            )
            SelectionContainer {
                Text(
                    text = url.short_address!!
                )
            }

        }


    }



}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    URLcard(URL("www.googladg;kadhjgadkhh.com", "www.shortURL.com"), Modifier)
}

