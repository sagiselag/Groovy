package sagi.sela.groovy.viewmodel.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import sagi.sela.groovy.model.details.PlaylistDetailsService
import javax.inject.Inject

class PlaylistDetailsViewModelFactory @Inject constructor(
    private val service: PlaylistDetailsService
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return PlaylistDetailsViewModel(service) as T
    }

}