package com.example.movieapp.presentation.movieDetails

import android.graphics.PorterDuff
import android.widget.RatingBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.movieapp.R
import com.example.movieapp.data.BASE_IMG_URL
import com.example.movieapp.domain.MovieDetailsData
import com.example.movieapp.ui.theme.MovieAppTheme
import com.google.accompanist.placeholder.placeholder

@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    movieDetails: MovieDetailsData
) {

    val painter = rememberImagePainter(
        data = BASE_IMG_URL + movieDetails.posterImgPath,
        builder = {
            crossfade(500)
        })

    val painterState = painter.state


    val isImgLoading = painterState is ImagePainter.State.Loading

    Column(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Image(
                painter = painter,
                //painterResource(id = R.drawable.movie1_poster),
                contentDescription = "movie Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .placeholder(visible = isImgLoading, color = Color.Gray),

                )

            IconButton(
                onClick = {
                    navController.navigateUp()
                }, modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 5.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back button",
                    tint = Color.White,
                    modifier = Modifier
                        .size(50.dp)
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(start = 15.dp, bottom = 10.dp)
            ) {
                Text(
                    text = movieDetails.name,
                    style = MaterialTheme.typography.h5,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                )
                val ratingBar = RatingBar(
                    LocalContext.current, null, android.R.attr.ratingBarStyleSmall
                ).apply {
                    rating = movieDetails.vote_average?.toFloat() ?: 0f
                    progressDrawable.setColorFilter(
                        android.graphics.Color.parseColor("#FFD700"),
                        PorterDuff.Mode.SRC_ATOP
                    )
                }

                Row {
                    AndroidView(
                        factory = { ratingBar }
                    )
                    Text(
                        text = movieDetails.vote_average.toString(),
                        style = MaterialTheme.typography.h5,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 7.dp)
                    )
                }

            }


        }
        MovieOverView(
            modifier = Modifier
                .weight(1f)
                .padding(start = 15.dp, top = 10.dp),
            overview = movieDetails.overview ?: ""
        )
    }

}

@Composable
fun MovieOverView(modifier: Modifier = Modifier, overview: String) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.synopsis),
            color = Color.White,
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = overview,
            color = Color.White,
            textAlign = TextAlign.Start,
            fontSize = 17.sp,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(
                start = 10.dp,
                end = 15.dp
            )
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewMovieDetailsScreen() {
    MovieAppTheme {
//        MovieDetailsScreen(
//            modifier = Modifier.background(LightGray),
//            //navController = NavController(),
//            movieDetails = MovieDetailsData(
//                overview =
//                "For best friends Becky and Hunter, life is all about conquering " +
//                        "fears and pushing limits. But after they climb 2,000 feet to the top of a remote, abandoned radio tower, they find themselves stranded with no way down. Now Becky and Hunter’s expert climbing skills will be put to the ultimate test as they desperately fight to survive the elements, a lack of supplies, and vertigo-inducing heights",
//                name = "Spiderman No Way Home", vote_average = 7.5
//            )
//        )
    }
}