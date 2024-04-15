package news.topheadlines.category

import androidx.compose.runtime.Composable
import multiplatformnews.composeapp.generated.resources.Res
import multiplatformnews.composeapp.generated.resources.*
import news.model.NewsCategory
import org.jetbrains.compose.resources.stringResource

@Composable
fun NewsCategory.toLocalizedString(): String =
    when (this) {
        NewsCategory.Business -> stringResource(Res.string.business)
        NewsCategory.Entertainment -> stringResource(Res.string.entertainment)
        NewsCategory.General -> stringResource(Res.string.general)
        NewsCategory.Health -> stringResource(Res.string.health)
        NewsCategory.Science -> stringResource(Res.string.science)
        NewsCategory.Sports -> stringResource(Res.string.sports)
        NewsCategory.Technology -> stringResource(Res.string.technology)
    }