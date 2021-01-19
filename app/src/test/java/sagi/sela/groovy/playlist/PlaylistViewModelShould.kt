package sagi.sela.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import petros.efthymiou.groovy.utils.getValueForTest
import sagi.sela.groovy.utils.BaseUnitTest
import java.lang.Exception
import java.lang.RuntimeException


class PlaylistViewModelShould: BaseUnitTest() {

    private val repository: PlaylistRepository = mock()
    private val playlists = mock<List<Playlist>>()
    private val expected = Result.success(playlists)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getPlaylistsFromRepository() = runBlockingTest {
        val viewModel = mockSuccessfulCase()

        viewModel.playlists.getValueForTest()

        verify(repository, times(1)).getPlaylists()
    }

    @Test
    fun emitsPlaylistsFromRepository() = runBlockingTest {
        val viewModel = mockSuccessfulCase()

        assertEquals(expected, viewModel.playlists.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveError(){
        val viewModel = mockFailureCase()

        assertEquals(exception, viewModel.playlists.getValueForTest()!!.exceptionOrNull())
//        assertEquals("other exception msg", viewModel.playlists.getValueForTest()!!.exceptionOrNull())
    }

    private fun mockFailureCase() : PlaylistViewModel {
        runBlockingTest {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(Result.failure<List<Playlist>>(exception))
                })
        }

        return PlaylistViewModel(repository)
    }

    private fun mockSuccessfulCase(): PlaylistViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }

        return PlaylistViewModel(repository)
    }
}