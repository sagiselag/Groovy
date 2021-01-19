package sagi.sela.groovy.playlist

import androidx.lifecycle.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlaylistViewModel(
    private val repository: PlaylistRepository
): ViewModel() {

    val playlists = liveData<Result<List<Playlist>>> {
        emitSource(repository.getPlaylists().asLiveData())
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
