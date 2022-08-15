package com.example.urlshortener.presentation.main_screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.SemanticsActions.OnClick
import androidx.compose.ui.text.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.urlshortener.common.Constants.historyScreenRoute
import com.example.urlshortener.presentation.theme.URLShortenerTheme


//better use a Column, but I used Constraint just to practice
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    navController: NavController
) {

    val state = viewModel.state.value
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        val constraints = ConstraintSet {
            val title = createRefFor("title")
            val enterText = createRefFor("enterText")
            val enterTextField = createRefFor("enterTextField")
            val resultText = createRefFor("resultText")
            val resultTextField = createRefFor("resultTextField")
            val convertButton = createRefFor("convertButton")
            val navigateToHistoryButton = createRefFor("navigateToHistoryButton")
            val loadingIndicator = createRefFor("loadingIndicator")

            constrain(title) {
                top.linkTo(parent.top, margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

            constrain(enterText) {
                top.linkTo(title.bottom, margin = 40.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

            constrain(enterTextField) {
                top.linkTo(enterText.bottom, margin = 5.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

            constrain(resultText) {
                top.linkTo(enterTextField.bottom, margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

            constrain(resultTextField) {
                top.linkTo(resultText.bottom, margin = 5.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

            constrain(loadingIndicator) {
                top.linkTo(resultText.bottom, margin = 5.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

            constrain(convertButton) {
                top.linkTo(enterTextField.bottom, margin = 95.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

            constrain(navigateToHistoryButton) {
                top.linkTo(convertButton.bottom, margin = 50.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        }

        ConstraintLayout(constraintSet = constraints, modifier = Modifier.fillMaxSize()) {
            Title(Modifier.layoutId("title"))


//            Box(modifier = Modifier
//                .background(MaterialTheme.colors.surface)
//                .layoutId("enterText")
//                .padding(10.dp),
//            ) {
//                Text(
//                    text = "Enter your URL here!",
//                    color = MaterialTheme.colors.onSurface
//                )
//            }

            Text(
                modifier = Modifier
                    .layoutId("enterText")
                    .background(MaterialTheme.colors.surface)
                    .padding(10.dp),
                text = "Enter your URL here!",
                color = MaterialTheme.colors.onSurface,
            )

            var URLtextField by rememberSaveable { mutableStateOf("") }
            TextField(
                modifier = Modifier
                    .layoutId("enterTextField")
                    .fillMaxWidth(0.9f)
                    .background(MaterialTheme.colors.surface),
                value = URLtextField,
                onValueChange = {
                    URLtextField = it
                },
                label = { Text(text = "URL") }
            )


            OutlinedButton(
                modifier = Modifier
                    .layoutId("convertButton")
                    .fillMaxWidth(0.3f)
                    .fillMaxHeight(0.08f),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colors.surface),
                content = {
                    Text(
                        text = "Apply",
                        color = MaterialTheme.colors.secondary,
                        fontSize = 20.sp
                    )
                },
                onClick = {
                    viewModel.getShortURL(URLtextField)
                },
                border = BorderStroke(width = 5.dp, color = Color.Black),
                shape = RoundedCornerShape(20.dp),
            )

            OutlinedButton(
                modifier = Modifier
                    .layoutId("navigateToHistoryButton")
                    .fillMaxWidth(0.3f)
                    .fillMaxHeight(0.08f),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colors.surface),
                content = {

                    Text(
                        text = "History",
                        color = MaterialTheme.colors.secondary,
                        fontSize = 20.sp
                    )
                },
                onClick = {
                    navController.navigate(historyScreenRoute)
                },
                border = BorderStroke(width = 5.dp, color = Color.Black),
                shape = RoundedCornerShape(20.dp),

            )



            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.layoutId("loadingIndicator"))
            }

            if (state.error.isNotEmpty()) {
                Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
            }

            if (state.currentURL!= null) {
                Text(
                    modifier = Modifier.layoutId("resultText"),
                    text = "Here is a shorter URL!"
                )

                SelectionContainer(modifier = Modifier.layoutId("resultTextField")) {
                    ClickableText(
                        text = AnnotatedString(state.currentURL.short_address ?: ""),
                        style = TextStyle(fontSize = 25.sp),
                        onClick = {
                            clipboardManager.setText(AnnotatedString(state.currentURL.short_address ?: ""))


                            Toast.makeText(context, "Copied!", Toast.LENGTH_SHORT).show()
                        }
                    )
                }

            }


        }



    }
}

@Composable
fun Title(modifier: Modifier) {
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
                        append("URL ")
                    }

                    withStyle(SpanStyle(color = MaterialTheme.colors.secondary, fontSize = 25.sp)) {
                        append("S")
                    }

                    withStyle(SpanStyle(color = MaterialTheme.colors.onSurface)) {
                        append("hortener")
                    }

                },
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.Center)

            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    URLShortenerTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colors.background
//        ) {
//            MainScreen()
//        }
//    }
//}