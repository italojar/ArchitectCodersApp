package es.architectcoders.spaceexplorer.components.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.architectcoders.domain.usecase.GetApodUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getApodUseCase: GetApodUseCase
) : ViewModel() {

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            getApodUseCase()
        }
    }
}