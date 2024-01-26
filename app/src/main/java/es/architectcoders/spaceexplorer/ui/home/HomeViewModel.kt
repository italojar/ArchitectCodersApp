package es.architectcoders.spaceexplorer.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.architectcoders.domain.Error
import es.architectcoders.spaceexplorer.ui.common.toDomain
import es.architectcoders.spaceexplorer.ui.common.toViewObject
import es.architectcoders.spaceexplorer.ui.model.ApodObject
import es.architectcoders.usecases.GetApodsUseCase
import es.architectcoders.usecases.RequestApodUseCase
import es.architectcoders.usecases.SaveApodFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val requestApodUseCase: RequestApodUseCase,
    private val getApodsUseCase: GetApodsUseCase,
    private val saveApodFavoriteUseCase: SaveApodFavoriteUseCase
) : ViewModel() {

    data class UiState(
        val loading: Boolean = false,
        val apodList: List<ApodObject> = emptyList(),
        val navigateTo: ApodObject? = null,
        val onBackPressed: Boolean = false,
        val error: Error? = null
    )

    private val _state = MutableStateFlow(UiState())

    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            val error: Error? = requestApodUseCase()
            _state.update { uiState -> uiState.copy(loading = false, error = error) }
            getApodsUseCase().collect { apodList ->
                _state.value = _state.value.copy(error = null, apodList = apodList.map { apod -> apod.toViewObject() })
            }
        }
    }

    fun onApodClicked(apod: ApodObject) {
        _state.value = _state.value.copy(navigateTo = apod)
    }

    fun saveApodAsFavourite(apod: ApodObject) {
        viewModelScope.launch {
            val error: Error? = saveApodFavoriteUseCase(apod.toDomain())
            _state.value = _state.value.copy(error = error)
        }
    }

    fun onApodNavigationDone() {
        _state.value = _state.value.copy(error = null, navigateTo = null)
    }

    fun onBackPressed() {
        _state.value = _state.value.copy(onBackPressed = true)
    }

    fun retry() {
        viewModelScope.launch {
            _state.value = _state.value.copy(error = null, loading = true)
            _state.update { uiState ->
                uiState.copy(loading = false, error = requestApodUseCase())
            }
        }
    }
}