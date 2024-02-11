package es.architectcoders.spaceexplorer.ui.rovers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.architectcoders.domain.Error
import es.architectcoders.spaceexplorer.framework.toError
import es.architectcoders.spaceexplorer.ui.common.toDomain
import es.architectcoders.spaceexplorer.ui.common.toViewObject
import es.architectcoders.spaceexplorer.ui.model.PhotoObject
import es.architectcoders.usecases.GetRoversUseCase
import es.architectcoders.usecases.RequestRoversUseCase
import es.architectcoders.usecases.SaveRoversFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoversViewModel @Inject constructor(
    private val requestRoversUseCase: RequestRoversUseCase,
    private val getRoversUseCase: GetRoversUseCase,
    private val saveRoverFavoriteUseCase: SaveRoversFavoriteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
//            _state.value = _state.value.copy(loading = true)
//            val error: Error? = requestRoversUseCase(date = "1000")
//            _state.update { uiState -> uiState.copy(loading = false, error = error) }
//            getRoversUseCase().collect { photoList ->
//                _state.value = _state.value.copy(error = null, photoList = photoList.map { photo ->
//                    photo.toViewObject()
//                })
//            }
            getRoversUseCase()
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect{ photos -> _state.update {UiState(photoList = photos.map {
                    it.toViewObject()
                })} }
        }
    }

    fun saveRoversAsFavourite(photo: PhotoObject) {
        viewModelScope.launch {
            val error: Error? = saveRoverFavoriteUseCase(photo.toDomain())
            _state.value = _state.value.copy(error = error)
        }
    }

    fun retry() {
        viewModelScope.launch {
            _state.value = _state.value.copy(error = null, loading = true)
            _state.update { uiState ->
                uiState.copy(loading = false, error = requestRoversUseCase(
                    date = "1000",
                    camera = "",
                    page = 1,
                    apiKey = "X2fTBf6Rjq8qA6Bz8AF43hSsloWps7A8RHLulOq3"
                    )
                )
            }
        }
    }

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            val error = requestRoversUseCase(
                date = "1000",
                camera = "",
                page = 1,
                apiKey = "X2fTBf6Rjq8qA6Bz8AF43hSsloWps7A8RHLulOq3"
            )
            _state.update { _state.value.copy(loading = false, error = error) }
        }

    }

    data class UiState(
        val loading: Boolean = false,
        val photoList: List<PhotoObject> = emptyList(),
        val error: Error? = null
    )
}