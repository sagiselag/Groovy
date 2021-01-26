package sagi.sela.groovy.viewmodel.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import sagi.sela.groovy.model.playlist.PlaylistRepository
import javax.inject.Inject

class PlaylistViewModelFactory @Inject constructor(
    private val repository: PlaylistRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return PlaylistViewModel(repository) as T
    }

}
