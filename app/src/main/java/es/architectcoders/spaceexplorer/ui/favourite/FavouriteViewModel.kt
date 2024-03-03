package es.architectcoders.spaceexplorer.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.architectcoders.domain.Error
import es.architectcoders.domain.NasaItem
import es.architectcoders.spaceexplorer.framework.toError
import es.architectcoders.usecases.GetFavoritesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FavouriteViewModel.UiState())
    val state: StateFlow<FavouriteViewModel.UiState> = _state.asStateFlow()

    private val favoriteList = mutableListOf<NasaItem>()

    init {
            viewModelScope.launch {
                getFavoritesUseCase()
                    .flowOn(Dispatchers.IO)
                    .onStart {
                        favoriteList.clear()
                        _state.update {
                            it.copy(loading = true, items = favoriteList)
                        }
                    }
                    .catch { cause ->
                        _state.update {
                            it.copy(
                                loading = false,
                                error = cause.toError()
                            )
                        }
                    }
                    .onEach { flow ->
                        flow.first { true }.also { items ->
                        favoriteList.addAll(items.sortedBy {
                            it.id})
                            favoriteList
                        }
                    }
                    .onCompletion {
                        if (favoriteList.isEmpty()) {
                            _state.update {
                                it.copy(loading = false, error = Error.Unknown("No hay favoritos"))
                            }
                        } else {
                            _state.update {
                                it.copy(
                                    loading = false,
                                    items = favoriteList/*.sortedByDescending { favorite -> favorite.date }*/)
                            }
                        }
                    }.collect()
            }
        }


    data class UiState(
        val loading: Boolean = false,
        val items: List<NasaItem>? = null,
        val onBackPressed: Boolean = false,
        val error: Error? = null
    )
}