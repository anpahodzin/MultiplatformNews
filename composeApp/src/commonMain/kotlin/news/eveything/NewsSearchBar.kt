package news.eveything

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import multiplatformnews.composeapp.generated.resources.Res
import multiplatformnews.composeapp.generated.resources.ic_close_24
import multiplatformnews.composeapp.generated.resources.ic_search_24
import multiplatformnews.composeapp.generated.resources.search
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import theme.AppColors
import theme.AppTheme

@Composable
fun NewsSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .shadow(AppTheme.sizes.small, AppTheme.shapes.circle)
            .background(AppTheme.colors.onSecondary, AppTheme.shapes.circle)
    ) {
        TextField(
            modifier = Modifier.weight(1f),
            value = value,
            singleLine = true,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                textColor = AppTheme.colors.onBackground,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = stringResource(Res.string.search),
                    color = AppColors.blueGray400,
                )
            }
        )

        AnimatedContent(value.isNotBlank()) { isNotEmpty ->
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clip(AppTheme.shapes.circle)
                    .clickable(isNotEmpty) { onValueChange("") }
                    .padding(AppTheme.sizes.medium),
                painter = painterResource(if (isNotEmpty) Res.drawable.ic_close_24 else Res.drawable.ic_search_24),
                contentDescription = stringResource(Res.string.search),
                tint = AppColors.blueGray400,
            )
        }
    }
}