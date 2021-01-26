package sagi.sela.groovy.details

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import sagi.sela.groovy.model.details.PlaylistDetails
import sagi.sela.groovy.model.details.PlaylistDetailsAPI
import sagi.sela.groovy.model.details.PlaylistDetailsService
import sagi.sela.groovy.utils.BaseUnitTest
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
class PlaylistDetailsServiceShould : BaseUnitTest() {
    private lateinit var service: PlaylistDetailsService
    private val id = "1"
    private val api : PlaylistDetailsAPI = mock()
    private val playlistDetails : PlaylistDetails = mock()
    private val exception = RuntimeException("Damn backend developers again")

    @Test
    fun fetchPlaylistDetailsFromAPI() = runBlockingTest {
        service = PlaylistDetailsService(api)

        service.fetchPlaylistDetails(id).single()

        verify(api, times(1)).fetchPlaylistDetails(id)
    }

    @Test
    fun convertValuesToFlowResultsAndEmitsThem() = runBlockingTest {
        mockSuccessfulCase()

        assertEquals(Result.success(playlistDetails), service.fetchPlaylistDetails(id).single())
    }

    @Test
    fun emitErrorResultWhenNetworkFails() = runBlockingTest {
        mockFailureCase()

        assertEquals("Something went wrong",
                service.fetchPlaylistDetails(id).first().exceptionOrNull()?.message)
    }

    private suspend fun mockFailureCase() {
        whenever(api.fetchPlaylistDetails(id))
                .thenThrow(exception)

        service = PlaylistDetailsService(api)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(api.fetchPlaylistDetails(id)).thenReturn(playlistDetails)

        service = PlaylistDetailsService(api)
    }
}