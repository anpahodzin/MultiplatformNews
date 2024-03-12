package news.eveything

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import theme.AppColors
import theme.AppTheme

@Composable
fun NewsSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        modifier = modifier
            .padding(horizontal = AppTheme.sizes.smallExtra)
            .border(
                width = AppTheme.sizes.borderMedium,
                color = AppTheme.colors.secondary,
                shape = AppTheme.shapes.circle
            )
            .background(AppTheme.colors.onSecondary, AppTheme.shapes.circle),
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
                text = "search",
                color = AppColors.blueGray400,
            )
        }
    )
}