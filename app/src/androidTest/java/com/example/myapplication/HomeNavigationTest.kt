package com.example.myapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.myapplication.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class HomeNavigationTest {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun navigationTest() {
        runBlocking {
            runTestCommand(
                listOf(
                    { homeScreen() },
                    { movieDetailScreen() },
                    { myListScreen() }
                )
            )

            delay(3_000)
        }
    }

    private fun homeScreen() {
        runBlocking {
            delay(2_000)
            onView(ViewMatchers.withId(com.example.home_feature.R.id.rvGenders)).perform(selectHomeMovie())
        }
    }

    private fun movieDetailScreen() {
        runBlocking {
            delay(2_000)
            onView(ViewMatchers.withId(com.example.home_feature.R.id.btMyList)).perform(updateFavorite())
            delay(2_000)
            onView(ViewMatchers.withId(com.example.home_feature.R.id.tbMovie)).perform(selectTabLayout(1))
            delay(1_000)
            onView(ViewMatchers.withId(com.example.home_feature.R.id.tbMovie)).perform(selectTabLayout(0))
            delay(1_000)
            onView(ViewMatchers.withId(com.example.home_feature.R.id.gvMovies)).perform(selectGridMovie())
            delay(1_000)
            onView(ViewMatchers.withId(com.example.home_feature.R.id.btnBack)).perform(forceClick())
            delay(1_000)
            onView(ViewMatchers.withId(com.example.home_feature.R.id.btnBack)).perform(forceClick())
        }
    }

    private fun myListScreen() {
        runBlocking {
            delay(1_000)
            onView(ViewMatchers.withId(R.id.bottomNavigation)).perform(selectBottomNavigation())
            delay(1_000)
            onView(ViewMatchers.withId(com.example.home_feature.R.id.gvMovies)).perform(selectGridMovie())
            delay(1_000)
            onView(ViewMatchers.withId(com.example.home_feature.R.id.btMyList)).perform(updateFavorite(false))
            delay(1_000)
            onView(ViewMatchers.withId(com.example.home_feature.R.id.btnBack)).perform(forceClick())
        }
    }
}