package news.topheadlines.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import news.model.NewsCategory
import theme.AppTheme

@Composable
fun NewsCategoryBar(
    modifier: Modifier = Modifier,
    selectedNewsCategory: NewsCategory,
    onCategoryClick: (NewsCategory) -> Unit,
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = AppTheme.sizes.medium),
    ) {
        items(NewsCategory.entries) { category ->
            CategoryButton(
                title = category.toLocalizedString(),
                isSelected = selectedNewsCategory == category,
                onClick = { onCategoryClick.invoke(category) }
            )
        }
    }
}

@Composable
private fun CategoryButton(
    modifier: Modifier = Modifier,
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Text(
        modifier = modifier
            .padding(vertical = AppTheme.sizes.small, horizontal = AppTheme.sizes.small)
            .shadow(AppTheme.sizes.small, AppTheme.shapes.circle)
            .background(if (isSelected) AppTheme.colors.secondary else AppTheme.colors.onSecondary)
            .clickable(onClick = onClick)
            .padding(AppTheme.sizes.smallExtra),
        text = title,
        textAlign = TextAlign.Center,
        color = if (isSelected) AppTheme.colors.onSecondary else AppTheme.colors.secondary
    )
}