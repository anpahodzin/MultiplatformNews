package news.topheadlines.category

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import news.model.NewsCategory
import theme.AppTheme
import view.AppButtonRow

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
            AppButtonRow(
                elevation = AppTheme.sizes.elevationLarge,
                isSelected = selectedNewsCategory == category,
                onClick = { onCategoryClick.invoke(category) }
            ) { _, color ->
                Text(
                    text = category.toLocalizedString(), textAlign = TextAlign.Center,
                    color = color
                )
            }
        }
    }
}