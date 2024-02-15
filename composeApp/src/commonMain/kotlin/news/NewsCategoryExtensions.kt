package news

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.compose.stringResource
import news.model.NewsCategory
import org.example.kmpnews.MR

@Composable
fun NewsCategory.toLocalizedString(): String =
    when (this) {
        NewsCategory.Business -> stringResource(MR.strings.business)
        NewsCategory.Entertainment -> stringResource(MR.strings.entertainment)
        NewsCategory.General -> stringResource(MR.strings.general)
        NewsCategory.Health -> stringResource(MR.strings.health)
        NewsCategory.Science -> stringResource(MR.strings.science)
        NewsCategory.Sports -> stringResource(MR.strings.sports)
        NewsCategory.Technology -> stringResource(MR.strings.technology)
    }