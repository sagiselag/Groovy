package sagi.sela.groovy.playlist

import kotlinx.coroutines.flow.Flow

class PlaylistRepository {
    suspend fun getPlaylists() : Flow<Result<List<Playlist>>> {
        TODO("Not yet implemented")
    }

}
