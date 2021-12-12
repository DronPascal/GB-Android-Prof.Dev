package com.geekbrains.tests

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.view.details.DetailsActivity
import com.geekbrains.tests.view.search.MainActivity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>
    private lateinit var context: Context

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activityTextView_NotNull() {
        scenario.onActivity {
            val searchEditText = it.findViewById<EditText>(R.id.searchEditText)
            assertNotNull(searchEditText)
        }
    }

    @Test
    fun activityTextView_HasText() {
        scenario.onActivity {
            val searchEditText = it.findViewById<TextView>(R.id.searchEditText)
            assertEquals("", searchEditText.text)
        }
    }

    @Test
    fun activityTextView_IsVisible() {
        scenario.onActivity {
            val searchEditText = it.findViewById<TextView>(R.id.searchEditText)
            assertEquals(View.VISIBLE, searchEditText.visibility)
        }
    }

    @Test
    fun activityButton_AreVisible() {
        scenario.onActivity {
            val toDetailsButton = it.findViewById<Button>(R.id.toDetailsActivityButton)
            assertEquals(View.VISIBLE, toDetailsButton.visibility)
        }
    }

    @Test
    fun activityButtonDecrement_IsWorking() {
        scenario.onActivity {
            val decrementButton = it.findViewById<Button>(R.id.decrementButton)
            val totalCountTextView = it.findViewById<TextView>(R.id.totalCountTextView)
            decrementButton.performClick()

            assertEquals("Number of results: -1", totalCountTextView.text)
        }
    }

    @After
    fun close() {
        scenario.close()
    }
}
