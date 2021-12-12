package com.geekbrains.tests

import ActivitySearchTester
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.view.search.MainActivity
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        TestCase.assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun searchEditText_NotNull() {
        scenario.onActivity {
            val searchEditText = it.findViewById<TextView>(R.id.searchEditText)
            TestCase.assertNotNull(searchEditText)
        }
    }

    @Test
    fun toDetailsButton_NotNull() {
        scenario.onActivity {
            val toDetailsButton = it.findViewById<TextView>(R.id.toDetailsActivityButton)
            TestCase.assertNotNull(toDetailsButton)
        }
    }

    @Test
    fun totalCountTextView_NotNull() {
        scenario.onActivity {
            val totalCountTextView = it.findViewById<TextView>(R.id.totalCountTextView)
            TestCase.assertNotNull(totalCountTextView)
        }
    }

    @Test
    fun searchEditText_HasHint() {
        val assertion: ViewAssertion = matches(withHint(R.string.search_hint))
        onView(withId(R.id.searchEditText)).check(assertion)
    }

    @Test
    fun toDetailsButton_HasText() {
        val assertion: ViewAssertion = matches(withText(R.string.to_details))
        onView(withId(R.id.toDetailsActivityButton)).check(assertion)
    }

    @Test
    fun totalCountTextView_HasText() {
        val assertion: ViewAssertion = matches(withText(R.string.results_count))
        onView(withId(R.id.totalCountTextView)).check(assertion)
    }

    @Test
    fun searchEditText_IsCompletelyDisplayed() {
        onView(withId(R.id.searchEditText)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun toDetailsButton_IsCompletelyDisplayed() {
        onView(withId(R.id.toDetailsActivityButton)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun totalCountTextView_AreInvisible() {
        onView(withId(R.id.totalCountTextView)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
    }

    @Test
    fun activitySearch_IsWorking() {
        onView(withId(R.id.searchEditText)).perform(click())
        onView(withId(R.id.searchEditText)).perform(replaceText("algol"), closeSoftKeyboard())
        onView(withId(R.id.searchEditText)).perform(pressImeActionButton())

        ActivitySearchTester.checkText()
    }


    @After
    fun close() {
        scenario.close()
    }
}
