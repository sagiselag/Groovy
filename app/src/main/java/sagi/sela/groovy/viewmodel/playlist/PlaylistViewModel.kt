package sagi.sela.groovy.viewmodel.playlist

import androidx.lifecycle.*
import kotlinx.coroutines.flow.onEach
import sagi.sela.groovy.model.playlist.Playlist
import sagi.sela.groovy.model.playlist.PlaylistRepository

class PlaylistViewModel(
    private val repository: PlaylistRepository
    ): ViewModel() {

    val loader = MutableLiveData<Boolean>()

    val playlists = liveData<Result<List<Playlist>>> {
        loader.postValue(true)

        emitSource(repository.getPlaylists()
            .onEach {
                loader.postValue(false)
            }
            .asLiveData())
    }

//    val playlists = MutableLiveData<Result<List<Playlist>>>()
//
//    init {
//        viewModelScope.launch {
//            repository.getPlaylists()
//                .collect {
//                    playlists.value = it
//                }
//        }
//    }
}
