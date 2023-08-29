package no.app.invenire.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import no.app.invenire.R
import no.app.invenire.domain.AdType
import no.app.invenire.ui.models.AdItemUI
import no.app.invenire.ui.theme.Typography

@Composable
fun AdCard(
    modifier: Modifier = Modifier,
    ad: AdItemUI,
    onHeartClicked: (AdItemUI) -> Unit,
) {
    Surface(modifier = modifier) {
        Column {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                AdImage(imageUrl = ad.imageUrl)
                FavoriteAdButton(
                    modifier = Modifier.align(Alignment.TopEnd),
                    isFavorite = ad.isFavorite,
                    onHeartClicked = { onHeartClicked(ad) },
                )
            }
            Text(
                text = ad.location ?: "Ukjent lokasjon", style = Typography.bodySmall
            )
            Text(
                text = ad.description ?: "Ukjent beskrivelse",
                style = Typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Text(
                text = ad.price?.let { "Totalpris $it kr" } ?: "Ukjent pris",
                style = Typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Composable
private fun AdImage(imageUrl: String?) {
    AsyncImage(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .fillMaxWidth(),
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = "Bilde av annonse",
        contentScale = ContentScale.FillWidth,
        placeholder = painterResource(id = R.drawable.placeholder),
        error = painterResource(id = R.drawable.placeholder),
    )
}

@Composable
private fun FavoriteAdButton(
    modifier: Modifier,
    isFavorite: Boolean,
    onHeartClicked: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = { onHeartClicked() }) {
        Icon(
            painter = painterResource(
                id = if (isFavorite) R.drawable.baseline_favorite_24 else R.drawable.outline_favorite_border_24
            ),
            tint = Color.White,
            contentDescription = "Favorite",
        )
    }
}

@Preview
@Composable
private fun PreviewAdCard() {
    AdCard(
        ad = AdItemUI(
            description = "Some long description about the ad or something like that",
            id = "1",
            adType = AdType.BAP,
            price = "1 000",
            location = "Oslo",
            imageUrl = "https://images.finncdn.no/dynamic/480x360c/2023/3/vertical-0/21/3/295/695/523_1539723838.jpg",
            isFavorite = false,
        ),
        onHeartClicked = {},
    )
}
