package com.rahulografy.testing3

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.AllOf.allOf
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlaylistsFeature {

    @get:Rule
    val mActivityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.rahulografy.testing3", appContext.packageName)
    }

    @Test
    fun displayScreenTitle() {
        assertDisplayed("Hello World!")
        // assertDisplayed(mActivityRule.activity.getString(R.string.playlists_title))
        assertDisplayed("Playlists")
    }

    @Test
    fun displaysPlaylists() {

        Thread.sleep(4000)

        assertRecyclerViewItemCount(R.id.rv_playlists, 10)

        onView(
            allOf(
                withId(R.id.tv_playlist_name),
                isDescendantOfA(
                    nthChildOf(
                        withId(R.id.rv_playlists),
                        0
                    )
                )
            )
        ).check(matches(withText("Hard Rock Cafe")))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.tv_playlist_category_name),
                isDescendantOfA(
                    nthChildOf(
                        withId(R.id.rv_playlists),
                        0
                    )
                )
            )
        ).check(matches(withText("rock")))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.iv_playlist_image),
                isDescendantOfA(
                    nthChildOf(
                        withId(R.id.rv_playlists),
                        0
                    )
                )
            )
        ).check(matches(withDrawable(R.mipmap.playlist)))
            .check(matches(isDisplayed()))
    }

    fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("position $childPosition of parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                if (view.parent !is ViewGroup) return false
                val parent = view.parent as ViewGroup

                return (
                    parentMatcher.matches(parent) &&
                        parent.childCount > childPosition &&
                        parent.getChildAt(childPosition) == view
                    )
            }
        }
    }
}
