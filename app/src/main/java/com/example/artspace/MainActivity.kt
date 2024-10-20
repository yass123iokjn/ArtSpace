package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {
    val firstArtwork = R.drawable.cri
    val secondArtwork = R.drawable.guernica
    val thirdArtwork = R.drawable.monaliza
    val fourthArtwork = R.drawable.nuit_etoilee

    var currentArtworkIndex by remember { mutableStateOf(0) }

    val artworks = listOf(
        Pair(R.string.art1, R.string.art1_year),
        Pair(R.string.art2, R.string.art2_year),
        Pair(R.string.art3, R.string.art3_year),
        Pair(R.string.art4, R.string.art4_year)
    )

    val artworkImages = listOf(firstArtwork, secondArtwork, thirdArtwork, fourthArtwork)

    val (title, year) = artworks[currentArtworkIndex]

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Affichage de l'image
        ArtworkDisplay(currentArtwork = artworkImages[currentArtworkIndex])
        Spacer(modifier = Modifier.size(16.dp))

        // Affichage du titre et de l'année
        ArtworkTitle(title = title, year = year)
        Spacer(modifier = Modifier.size(25.dp))

        // Les boutons "Previous" et "Next"
        Row(
            modifier = modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFeba443)),
                onClick = {
                    // On met à jour l'image précédente (on cycle en arrière)
                    currentArtworkIndex = (currentArtworkIndex - 1 + artworks.size) % artworks.size
                }
            ) {
                Text(text = "Previous", fontSize = 16.sp)
            }

            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFeba443)),
                onClick = {
                    // On met à jour l'image suivante (on cycle en avant)
                    currentArtworkIndex = (currentArtworkIndex + 1) % artworks.size
                }
            ) {
                Text(text = "Next", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun ArtworkDisplay(
    @DrawableRes currentArtwork: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(currentArtwork),
        contentDescription = null, // Tu peux ajouter un stringResource si besoin
        modifier = modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun ArtworkTitle(
    @StringRes title: Int,
    @StringRes year: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFeba443))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )

        Text(
            text = "— ${stringResource(id = year)} —",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}