package es.architectcoders.spaceexplorer.ui.mars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.architectcoders.domain.Error
import es.architectcoders.domain.NotificationsItem
import es.architectcoders.spaceexplorer.framework.toError
import es.architectcoders.usecases.GetNotificationsUseCase
import es.architectcoders.usecases.RequestNotificationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarsViewModel @Inject constructor(
    private val requestNotificationsUseCase: RequestNotificationsUseCase,
    getNotificationsUseCase: GetNotificationsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()
    init {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            val error = requestNotificationsUseCase()
            _state.update { _state.value.copy(loading = false, error = error) }

            getNotificationsUseCase()
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect{ notifications -> _state.update {
                    UiState(notificationsList = notifications) }
                }
        }
    }
    fun retry() {
        viewModelScope.launch {
            _state.value = _state.value.copy(error = null, loading = true)
            _state.update { uiState ->
                uiState.copy(loading = false, error = requestNotificationsUseCase()
                )
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val notificationsList: List<NotificationsItem>? = null,
        val error: Error? = null
    )
}