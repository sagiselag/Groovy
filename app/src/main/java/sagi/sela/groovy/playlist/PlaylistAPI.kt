package sagi.sela.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface PlaylistAPI {
    suspend fun fetchAllPlaylists() : List<Playlist> {
        TODO("Not yet implemented")
    }

}
