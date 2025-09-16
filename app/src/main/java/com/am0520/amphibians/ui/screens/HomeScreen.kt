package com.am0520.amphibians.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.am0520.amphibians.R
import com.am0520.amphibians.model.Amphibian
import com.am0520.amphibians.ui.AmphibiansUiState
import com.am0520.amphibians.ui.theme.AmphibiansTheme

@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when (amphibiansUiState) {
        is AmphibiansUiState.Loading -> {}

        is AmphibiansUiState.Success -> {
            AmphibianList(
                amphibianList = amphibiansUiState.amphibians,
                contentPadding = contentPadding,
                modifier = modifier
            )
        }

        is AmphibiansUiState.Error -> {}
    }
}

@Composable
private fun AmphibianList(
    amphibianList: List<Amphibian>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
    ) {
        items(
            items = amphibianList
        ) { amphibian ->
            AmphibianItem(amphibian = amphibian)
        }
    }
}

@Composable
private fun AmphibianItem(
    amphibian: Amphibian,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_medium))
        ) {
            Text(
                text = stringResource(
                    R.string.name_type_formatting,
                    amphibian.name,
                    amphibian.type
                ),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.loading_img),
                error = painterResource(R.drawable.ic_broken_image),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            )
            Text(
                text = amphibian.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
            )
        }
    }
}

@Preview
@Composable
private fun AmphibianListPreview() {
    AmphibiansTheme {
        AmphibianList(
            amphibianList = listOf(
                Amphibian(
                    name = "Frog",
                    type = "Tree Frog",
                    description = "A frog is any member of a diverse and largely carnivorous group of short-bodied, tailless amphibians composing the order Anura (literally without tail in Ancient Greek). The oldest fossil " +
                        "proto-frog appeared in the early Triassic of Madagascar, but molecular clock dating suggests their origins may extend further back to the Permian, 265 million years ago.",
                    imageUrl = "",
                ),
                Amphibian(
                    name = "Toad",
                    type = "True Toad",
                    description = "Toads are frogs (members of the order Anura) that are characterized by dry, leathery skin, short legs, and large bumps covering the parotoid glands behind the eyes. They are found all over the world except for Antarctica and Australia. Toads tend to be more terrestrial than other frogs, although they still need to live near water to reproduce.",
                    imageUrl = "",
                ),
                Amphibian(
                    name = "Salamander",
                    type = "True Salamander",
                    description = "Salamanders are a group of amphibians typically characterized by a lizard-like appearance, with slender bodies, blunt snouts, short limbs projecting at right angles to the body, and the presence of a tail in both larvae and adults. All present-day salamander families are grouped together under the order Urodela (sometimes called Caudata) and are widely distributed throughout the Northern Hemisphere.",
                    imageUrl = "",
                ),
            )
        )
    }
}

@Preview
@Composable
private fun AmphibianItemPreview() {
    AmphibiansTheme {
        AmphibianItem(
            Amphibian(
                name = "Frog",
                type = "Tree Frog",
                description = "A frog is any member of a diverse and largely carnivorous group of short-bodied, tailless amphibians composing the order Anura (literally without tail in Ancient Greek). The oldest fossil " +
                    "proto-frog appeared in the early Triassic of Madagascar, but molecular clock dating suggests their origins may extend further back to the Permian, 265 million years ago.",
                imageUrl = "",
            )
        )
    }
}
