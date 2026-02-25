package com.cheshuina_antonova.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cheshuina_antonova.myapplication.data.Datasource
import com.cheshuina_antonova.myapplication.model.Movie
import com.cheshuina_antonova.myapplication.ui.theme.Lab9_AndroidTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab9_AndroidTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieCatalogApp()
                }

            }
        }
    }
}

@Composable
fun MovieImage(movie: Movie, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = movie.imageRes),
        contentDescription = stringResource(movie.titleRes),
        modifier = modifier
            .fillMaxWidth()
            .height(450.dp)
    )
}
@Composable
fun MovieTitle(movie: Movie, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(movie.titleRes),
        modifier = modifier
            .padding(16.dp),
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
fun MovieDescription(movie: Movie, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(movie.descriptionRes),
        modifier = modifier
            .padding(horizontal = 16.dp),
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun NavigationPanel(
    currentIndex: Int,
    totalCount: Int,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = onPrevious) {
            Text("← Предыдущая")
        }
        Text("${currentIndex + 1}/$totalCount")
        Button(onClick = onNext) {
            Text("Следующая →")
        }
    }
}

@Composable
fun MovieCard(
    movie: Movie,
    currentIndex: Int,
    totalCount: Int,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            MovieImage(movie = movie)
            MovieTitle(movie = movie)
            MovieDescription(movie = movie)
            NavigationPanel(
                currentIndex = currentIndex,
                totalCount = totalCount,
                onPrevious = onPrevious,
                onNext = onNext
            )
        }
    }
}
@Composable
fun MovieCatalogApp() {
    val movies = remember { Datasource().loadMovieList() }
    var currentIndex by rememberSaveable { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        MovieCard(
            movie = movies[currentIndex],
            currentIndex = currentIndex,
            totalCount = movies.size,
            onPrevious = {
                currentIndex = if (currentIndex > 0) currentIndex - 1 else movies.size - 1
            },
            onNext = {
                currentIndex = if (currentIndex < movies.size - 1) currentIndex + 1 else 0
            }
        )
    }
}

