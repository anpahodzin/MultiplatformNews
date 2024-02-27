package news.detailed.mapper

import multiplatformnews.composeapp.generated.resources.Res
import news.detailed.NewsDetailedUiEvent
import org.jetbrains.compose.resources.getString

internal suspend fun NewsDetailedUiEvent.toMessage() =
    when (this) {
        NewsDetailedUiEvent.AddedFavoriteNews -> getString(Res.string.news_added_to_favorites)
        NewsDetailedUiEvent.RemovedFavoriteNews -> getString(Res.string.news_removed_from_favorites)
    }