package com.example.mypokedex.ui.composables.pokemon_list

import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.mypokedex.R
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.utils.Constants
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun PokemonListItem(
    pokemon: Pokemon,
    fakeId: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.size(75.dp),
        onClick = { onClick() }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                pokemon.name.replaceFirstChar { it.uppercase() },
                Modifier
                    .padding(20.dp)
                    .weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 10.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Card(
                modifier = Modifier
                    .background(
                        colorResource(R.color.transparent_gray),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .shadow(
                        elevation = 25.dp,
                        shape = RoundedCornerShape(10.dp)
                    ),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)

            ) {

                val imageState = remember { mutableStateOf(true) }

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(Constants.Network.retrieveImageUrl(fakeId))
                        // .crossfade(true)
                        .build(),
                    onSuccess = { imageState.value = false },
                    contentDescription = null,
                    error = painterResource(R.drawable.gengar),
                    modifier = Modifier.size(75.dp),
                )

                if (imageState.value) {
                    Image(
                        modifier = Modifier
                            .alpha(0.6f)
                            .size(50.dp),
                        painter = rememberDrawablePainter(
                            drawable = getDrawable(
                                LocalContext.current,
                                R.drawable.pokeball_gif
                            )
                        ),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonList() {
    val pokemon = Pokemon("Buba")

    PokemonListItem(pokemon, 1) {}
}