package news.detailed.mapper

import multiplatformnews.composeapp.generated.resources.Res
import multiplatformnews.composeapp.generated.resources.news_added_to_favorites
import multiplatformnews.composeapp.generated.resources.news_removed_from_favorites
import news.detailed.NewsDetailedUiEvent
import org.jetbrains.compose.resources.getString

internal suspend fun NewsDetailedUiEvent.toMessage() =
    when (this) {
        NewsDetailedUiEvent.AddedFavoriteNews -> getString(Res.string.news_added_to_favorites)
        NewsDetailedUiEvent.RemovedFavoriteNews -> getString(Res.string.news_removed_from_favorites)
    }