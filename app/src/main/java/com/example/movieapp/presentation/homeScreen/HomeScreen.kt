package com.example.movieapp.presentation.homeScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieapp.R.string
import com.example.movieapp.presentation.components.AutoSlider
import com.example.movieapp.presentation.util.Screen
import com.example.movieapp.ui.theme.DarkRed
import com.example.movieapp.ui.theme.MovieAppTheme


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: MovieState,
    navController: NavController
) {
    state.movieData?.let {
        Column(modifier = modifier.fillMaxSize()) {
            ScreenHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            AutoSlider(
                modifier = Modifier
                    .fillMaxWidth(),
                movies = it.take(6),
                indicatorWidth = 15.dp,
                onImgClicked = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "movieData",
                        it
                    )
                    navController.navigate(Screen.MovieDetailsScreen.route)
                }

            )
        }
    }

}

@Composable
fun ScreenHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(string.app_title),
            color = DarkRed,
            textAlign = TextAlign.Center,
            fontSize = 35.sp,
            fontWeight = FontWeight.W900,
            fontFamily = FontFamily.Cursive,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = stringResource(string.theaters),
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = stringResource(string.now_playing),
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Default,
            modifier = Modifier.fillMaxWidth()
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewHomeScreen() {
    MovieAppTheme {
//        HomeScreen(
//            Modifier
//                .background(LightGray),
//            MovieState()
//        )
    }
}