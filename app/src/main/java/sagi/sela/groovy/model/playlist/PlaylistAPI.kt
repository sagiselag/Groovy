package sagi.sela.groovy.model.playlist

import retrofit2.http.GET

interface PlaylistAPI {

    @GET("playlists")
    suspend fun fetchAllPlaylists() : List<PlaylistRaw>

}
