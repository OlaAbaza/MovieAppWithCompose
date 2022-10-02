package com.example.movieapp.presentation.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.movieapp.data.BASE_IMG_URL
import com.example.movieapp.domain.MovieDetailsData
import com.example.movieapp.ui.theme.LightGray
import com.example.movieapp.ui.theme.MovieAppTheme
import com.google.accompanist.pager.*
import com.google.accompanist.placeholder.placeholder
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue


@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoSlider(
    modifier: Modifier = Modifier,
    movies: List<MovieDetailsData>,
    indicatorWidth: Dp = 7.dp,
    onImgClicked: (MovieDetailsData) -> Unit
) {
    val pagerState = rememberPagerState(movies.size)

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2000)
            tween<Float>(600)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount)
            )
        }
    }

    Column(modifier = modifier) {

        HorizontalPager(
            count = movies.size,
            state = pagerState,
            modifier = Modifier
                .heightIn(max = 300.dp)
                .align(CenterHorizontally)
                .padding(top = 40.dp),
            contentPadding = PaddingValues(horizontal = 110.dp)

        ) { page ->

            // Calculate the absolute offset for the current page from the
            // scroll position. We use the absolute value which allows us to mirror
            // any effects for both directions

            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

            PagerItem(
                movie = movies[page],
                pageOffset = pageOffset,
                isRotate = currentPage != page,
                onImgClicked = onImgClicked
            )
        }

        // dots indicator
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(16.dp),
            activeColor = Color.Red,
            inactiveColor = Color.Gray,
            indicatorWidth = indicatorWidth,
            spacing = 8.dp

        )

    }

}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun PagerItem(
    modifier: Modifier = Modifier,
    movie: MovieDetailsData,
    pageOffset: Float,
    onImgClicked: (MovieDetailsData) -> Unit,
    isRotate: Boolean
) {
    //  val angle = if (isRotate) 10f else 0f

    val painter = rememberImagePainter(
        data = BASE_IMG_URL + movie.posterImgPath,
        builder = {
            crossfade(500)
        })

    val painterState = painter.state


    val isImgLoading = painterState is ImagePainter.State.Loading

    Card(
        modifier = modifier
            .graphicsLayer {
                //     rotationZ = angle
                // We animate the scaleX + scaleY, between 85% and 100%
                lerp(
                    start = 0.85f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                ).also { scale ->
                    scaleX = scale
                    scaleY = scale
                }

                // We animate the alpha, between 50% and 100%
                alpha = lerp(
                    start = 0.5f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            }
            .size(width = 180.dp, height = 300.dp)
            .placeholder(visible = isImgLoading, color = Color.Gray)
            .clickable { onImgClicked(movie) },
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(Modifier.fillMaxHeight()) {
            Image(
                painter = painter,
                //painterResource(id = R.drawable.movie1_poster),
                contentDescription = "movie Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            /*     Text(
                     text = movie.name,
                     modifier = Modifier
                         .align(Alignment.BottomCenter)
                         .padding(
                             bottom = 10.dp,
                             start = 15.dp,
                             end = 15.dp
                         ),
                     style = MaterialTheme.typography.h5,
                     color = Color.White,
                     fontWeight = FontWeight.Bold,
                     textAlign = TextAlign.Center,
                     fontSize = 18.sp
                 )*/

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewAutoSlider() {
    MovieAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightGray)
        ) {
//            AutoSlider(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                movies = listOf(
//                    MovieDetailsData(name = "Spiderman No Way Home"),
//                    MovieDetailsData(name = "Spiderman No Way Home"),
//                    MovieDetailsData(name = "Spiderman No Way Home")
//
//                )
//            )
        }
    }
}