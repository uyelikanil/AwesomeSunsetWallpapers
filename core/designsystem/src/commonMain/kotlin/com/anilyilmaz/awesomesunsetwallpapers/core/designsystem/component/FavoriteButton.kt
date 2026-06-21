package com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

val FavoriteOutlineIcon: ImageVector
    get() = Icons.Outlined.FavoriteBorder

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(36.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(30.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .blur(
                        radius = 5.dp,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded,
                    )
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.18f),
                        shape = CircleShape,
                    ),
            )
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.42f),
                        shape = CircleShape,
                    )
                    .border(
                        width = 0.75.dp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.32f),
                        shape = CircleShape,
                    ),
            )
            FavoriteIcon(
                isFavorite = isFavorite,
                contentDescription = contentDescription,
                modifier = Modifier.size(18.dp),
            )
        }
    }
}

@Composable
fun FavoriteIcon(
    isFavorite: Boolean,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    selectedTint: Color? = null,
    unselectedTint: Color? = null,
) {
    Icon(
        imageVector = if (isFavorite) Icons.Filled.Favorite else FavoriteOutlineIcon,
        contentDescription = contentDescription,
        tint = if (isFavorite) {
            selectedTint ?: MaterialTheme.colorScheme.primary
        } else {
            unselectedTint ?: MaterialTheme.colorScheme.onSurface
        },
        modifier = modifier,
    )
}
