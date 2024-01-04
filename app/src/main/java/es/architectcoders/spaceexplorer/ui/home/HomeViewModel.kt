package es.architectcoders.spaceexplorer.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.architectcoders.domain.usecase.GetApodUseCase
import es.architectcoders.spaceexplorer.common.toViewObject
import es.architectcoders.spaceexplorer.model.ApodObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getApodUseCase: GetApodUseCase
) : ViewModel() {

    data class UiState(
        val loading: Boolean = false,
        val apod: ApodObject? = null,
        val navigateTo: ApodObject? = null,
        val onBackPressed: Boolean = false
    )

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(apod = getApodUseCase().toViewObject())
        }
    }

    fun onApodClicked(apod: ApodObject) {
        _state.value = _state.value.copy(navigateTo = apod)
    }

    fun saveApodAsFavourite(apod: ApodObject) {
        Log.d("HomeViewModel", "saveApodAsFavourite")
    }

    fun onApodNavigationDone() {
        _state.value = _state.value.copy(navigateTo = null)
    }

    fun onBackPressed() {
        _state.value = _state.value.copy(onBackPressed = true)
    }
}