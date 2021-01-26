package sagi.sela.groovy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist
import org.hamcrest.core.AllOf.allOf
import org.junit.Test
import sagi.sela.groovy.model.playlist.idlingResource

class PlaylistDetailsFeature : BaseUITest() {


    @Test
    fun displaysPlaylistNameAndDetails() {
        navigateToPlaylistDetails(0)

        assertDisplayed("Hard Rock Cafe")

        assertDisplayed("Rock your senses with this timeless signature vibe list. \n\n " +
                "• Poison \n " +
                "• You shook me all night \n " +
                "• Zombie \n " +
                "• Rock'n Me \n " +
                "• Thunderstruck \n " +
                "• I Hate Myself for Loving you \n " +
                "• Crazy \n " +
                "• Knockin' on Heavens Door")
    }

    @Test
    fun displaysLoaderWhileFetchingThePlaylistDetails() {
        IdlingRegistry.getInstance().unregister(idlingResource)

        Thread.sleep(2000)
        navigateToPlaylistDetails(0)

        assertDisplayed(R.id.details_loader)
    }

    @Test
    fun hidesLoader() {
        navigateToPlaylistDetails(0)

        assertNotDisplayed(R.id.details_loader)
    }

    @Test
    fun displaysErrorMessageWhenNetworkFails() {
        navigateToPlaylistDetails(1)

        assertDisplayed(R.string.generic_error)
    }

    @Test
    fun errorMessageDisappearsAfterTwoSeconds() {
        navigateToPlaylistDetails(2)

        // Snackbar has 2750ms delay
        Thread.sleep(3000)

        assertNotExist(R.string.generic_error)
    }

    private fun navigateToPlaylistDetails(position : Int) {
        onView(allOf(withId(R.id.playlist_image),
                isDescendantOfA(nthChildOf(withId(R.id.playlists_list), position))))
                .perform(click())
    }
}