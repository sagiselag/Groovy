package sagi.sela.groovy.model.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import sagi.sela.groovy.interactor.PlaylistMapper
import javax.inject.Inject

class PlaylistRepository @Inject constructor (
        private val service: PlaylistService,
        private val mapper : PlaylistMapper
) {
    suspend fun getPlaylists() : Flow<Result<List<Playlist>>> =
        service.fetchPlaylists().map {
            if(it.isSuccess)
                Result.success(mapper(it.getOrNull()!!))
            else
                Result.failure(it.exceptionOrNull()!!)
        }

}
