package es.architectcoders.spaceexplorer.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.architectcoders.domain.Apod
import es.architectcoders.domain.Error
import es.architectcoders.domain.Photo
import es.architectcoders.spaceexplorer.framework.toError
import es.architectcoders.usecases.GetFavoritesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class FavouriteViewModel @Inject constructor(
//    private val getFavoritesUseCase: GetFavoritesUseCase
//) : ViewModel() {
//
//    private val _state = MutableStateFlow(FavouriteViewModel.UiState())
//    val state: StateFlow<FavouriteViewModel.UiState> = _state.asStateFlow()
//
//    private val favoriteList = mutableListOf<Any>()
//
//    fun getFavorites() {
//        viewModelScope.launch {
//            getFavoritesUseCase()
//                .flowOn(Dispatchers.IO)
//                .onStart {
//                    favoriteList.clear()
//                    _state.update {
//                        it.copy(loading = true, items = favoriteList)
//                    }
//                }
//                .catch { cause ->
//                    _state.update {
//                        it.copy(
//                            loading = false,
//                            error = cause.toError()
//                        )
//                    }
//                }
//                .onEach { flow ->
//                    flow.first { true }.also { items ->
////                        favoriteList.addAll(items.sortedBy { it.type })
//                        favoriteList
//                    }
//                }
//                .onCompletion {
//                    if (favoriteList.isEmpty()) {
//                        _state.update {
//                            it.copy(loading = false, error = Error.Unknown("No hay favoritos"))
//                        }
//                    } else {
//                        _state.update {
//                            it.copy(
//                                loading = false,
//                                items = favoriteList/*.sortedByDescending { favorite -> favorite.date }*/)
//                        }
//                    }
//                }.collect()
//        }
//    }
//    data class UiState(
//        val loading: Boolean = false,
//        val items: MutableList<Any>? = null,
//        val onBackPressed: Boolean = false,
//        val error: Error? = null
//    )
//}



@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()
    
    init {
        viewModelScope.launch {
            getFavoritesUseCase()
                .onEach { list ->
                    _state.value = UiState(favoriteList = list, loading = false)
                }
                .catch { cause ->
                    _state.value = UiState(error = cause.toError())
                }.collectToList()
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val favoriteList: List<Any> = emptyList(),
        val onBackPressed: Boolean = false,
        val error: Error? = null
    )

    private suspend fun Flow<List<Any>>.collectToList(): List<Any> {
        val resultList = mutableListOf<Any>()
        this.collect { list ->
            resultList.addAll(list)
        }
        return resultList
    }
}