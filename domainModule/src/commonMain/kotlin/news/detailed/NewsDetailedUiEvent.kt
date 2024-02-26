package news.detailed

sealed class NewsDetailedUiEvent {
    data object AddedFavoriteNews : NewsDetailedUiEvent()

    data object RemovedFavoriteNews : NewsDetailedUiEvent()
}