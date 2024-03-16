package news.detailed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.zIndex
import multiplatformnews.composeapp.generated.resources.Res
import multiplatformnews.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import theme.AppColors
import theme.AppTheme
import view.ButtonBack
import view.circleButton

@Composable
fun NewsToolbar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    isFavoriteFilled: Boolean,
    title: String,
) {
    Row(
        modifier = modifier
            .zIndex(1f)
            .padding(horizontal = AppTheme.sizes.medium, vertical = AppTheme.sizes.small),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ButtonBack(onClick = onBackClick)
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = AppTheme.typography.lightTitleHeaderText,
            textAlign = TextAlign.Center
        )
        val favoriteImage =
            if (isFavoriteFilled) Res.drawable.ic_favorite_fill_24 else Res.drawable.ic_favorite_24
//        val favoriteTint = if(isFavoriteFilled) Res.drawable.ic_favorite_fill_24 else Res.drawable.ic_favorite_24
        Image(
            modifier = modifier.circleButton(onFavoriteClick),
            painter = painterResource(favoriteImage),
            contentDescription = stringResource(Res.string.favorite),
            colorFilter = ColorFilter.tint(AppColors.flamingo600),
        )
    }
}