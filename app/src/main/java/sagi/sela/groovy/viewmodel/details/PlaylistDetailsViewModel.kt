package sagi.sela.groovy.viewmodel.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import sagi.sela.groovy.model.details.PlaylistDetails
import sagi.sela.groovy.model.details.PlaylistDetailsService

class PlaylistDetailsViewModel(
    private val service: PlaylistDetailsService
) : ViewModel() {

    val loader = MutableLiveData<Boolean>()

    val playlistDetails: MutableLiveData<Result<PlaylistDetails>> = MutableLiveData()

    fun getPlaylistDetails(id: String) {

        viewModelScope.launch {
            loader.postValue(true)
            service.fetchPlaylistDetails(id)
                    .onEach {
                        loader.postValue(false)
                    }
                    .collect {
                        playlistDetails.postValue(it)
                    }
        }
    }

}
