package com.example.mypokedex.ui.composables.pokemon_details.pokemon_details_specs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import com.example.mypokedex.R
import com.example.mypokedex.domain.model.Type
import com.example.mypokedex.domain.model.TypeDetail
import com.example.mypokedex.utils.Constants

@Composable
fun PokemonTypeImage(
    type : TypeDetail
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(Constants.Network.retrieveTypeImageUrl(type.url))
            .build(),
        contentDescription = null,
        modifier = Modifier
            .size(60.dp),
        error = {
            if (LocalInspectionMode.current) {
                Image(
                    painter = painterResource(R.drawable.gengar),
                    contentDescription = null,
                )
            }
        }
    )

    Spacer(Modifier.width(10.dp))
}