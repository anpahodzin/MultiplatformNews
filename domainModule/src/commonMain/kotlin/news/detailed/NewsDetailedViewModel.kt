package news.detailed

import core.ComponentViewModel
import core.runCatchingCancellable
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import news.NewsRepository
import news.model.News

class NewsDetailedViewModel(
    private val selectedNews: News,
    private val repository: NewsRepository
) : ComponentViewModel() {

    private val _state =
        MutableStateFlow<NewsDetailedUiState>(NewsDetailedUiState.Loading)
    val state = _state.asStateFlow()

    private val _eventChannel = Channel<NewsDetailedUiEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    init {
        updateState()
    }

    private fun updateState() {
        launch {
            _state.tryEmit(NewsDetailedUiState.Loading)
            runCatchingCancellable {
                repository.isFavouriteNews(selectedNews)
            }.onSuccess {
                _state.tryEmit(NewsDetailedUiState.Data(selectedNews, it))
            }.onFailure {
                _state.tryEmit(NewsDetailedUiState.Error)
            }
        }
    }

    fun onFavoritePressed() {
        launch {
            runCatchingCancellable {
                repository.addOrDeleteFavoriteNews(selectedNews)
            }.onSuccess { isFavoriteNews ->
                _state.tryEmit(NewsDetailedUiState.Data(selectedNews, isFavoriteNews))
                _eventChannel.send(
                    if (isFavoriteNews) {
                        NewsDetailedUiEvent.AddedFavoriteNews
                    } else {
                        NewsDetailedUiEvent.RemovedFavoriteNews
                    }
                )
            }.onFailure {
                _state.tryEmit(NewsDetailedUiState.Error)
            }
        }
    }
}