package es.architectcoders.spaceexplorer.components.home

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    init {
        Log.i("HomeViewModel", "init")
    }
}