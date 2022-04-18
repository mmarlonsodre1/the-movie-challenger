package com.example.myapplication.utils

import android.view.View
import android.widget.GridView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import com.example.myapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher

fun runTestCommand(test: List<() -> Unit>) {
    runBlocking {
        test.forEach {
            delay(2_000)
            it.invoke()
        }
    }
}

fun selectHomeMovie(): ViewAction {
    return object : ViewAction {

        override fun getConstraints(): Matcher<View>? {
            return CoreMatchers.allOf(
                ViewMatchers.isDisplayed(),
                ViewMatchers.isAssignableFrom(RecyclerView::class.java)
            )
        }

        override fun getDescription() = ""

        override fun perform(uiController: UiController?, view: View) {
            ((view as RecyclerView)[0]
                .findViewById(com.example.home_feature.R.id.rvMovies) as RecyclerView)[0].performClick()
        }
    }
}

fun updateFavorite(isAdd: Boolean = true): ViewAction {
    return object : ViewAction {

        override fun getConstraints(): Matcher<View>? {
            return CoreMatchers.allOf(
                ViewMatchers.isDisplayed()
            )
        }

        override fun getDescription() = ""

        override fun perform(uiController: UiController?, view: View) {
            val btn = view as MaterialButton
            if ((isAdd && btn.text == "Add Lista") || !isAdd) btn.performClick()
        }
    }
}

fun selectTabLayout(position: Int = 0): ViewAction {
    return object : ViewAction {

        override fun getConstraints(): Matcher<View>? {
            return CoreMatchers.allOf(
                ViewMatchers.isDisplayed(),
                ViewMatchers.isAssignableFrom(TabLayout::class.java)
            )
        }

        override fun getDescription() = ""

        override fun perform(uiController: UiController?, view: View) {
            (view as TabLayout).getTabAt(position)?.select()
        }
    }
}

fun selectGridMovie(): ViewAction {
    return object : ViewAction {

        override fun getConstraints(): Matcher<View>? {
            return CoreMatchers.allOf(
                ViewMatchers.isDisplayed(),
                ViewMatchers.isAssignableFrom(GridView::class.java)
            )
        }

        override fun getDescription() = ""

        override fun perform(uiController: UiController?, view: View) {
            ((view as GridView)[0]
                .findViewById(com.example.home_feature.R.id.ivMovie) as AppCompatImageView).performClick()
        }
    }
}

fun selectBottomNavigation(): ViewAction {
    return object : ViewAction {

        override fun getConstraints(): Matcher<View>? {
            return CoreMatchers.allOf(
                ViewMatchers.isDisplayed(),
                ViewMatchers.isAssignableFrom(BottomNavigationView::class.java)
            )
        }

        override fun getDescription() = ""

        override fun perform(uiController: UiController?, view: View) {
            (view as BottomNavigationView).selectedItemId = R.id.my_list_navigation
        }
    }
}

fun forceClick(): ViewAction {
    return object : ViewAction {

        override fun getConstraints(): Matcher<View>? {
            return CoreMatchers.allOf(
                ViewMatchers.isDisplayed()
            )
        }

        override fun getDescription() = ""

        override fun perform(uiController: UiController?, view: View) {
            view.performClick()
            uiController?.loopMainThreadUntilIdle()
        }
    }
}