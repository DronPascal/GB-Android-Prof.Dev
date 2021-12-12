import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.geekbrains.tests.R
import org.hamcrest.Matcher

/**
 * Created by dronpascal on 12.12.2021.
 */
class ActivitySearchTester {

    companion object {

        fun checkText() {
            Espresso.onView(ViewMatchers.isRoot()).perform(delay())
            Espresso.onView(ViewMatchers.withId(R.id.totalCountTextView))
                .check(ViewAssertions.matches(ViewMatchers.withText("Number of results: 2706")))
        }

        private fun delay(): ViewAction? {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View> = ViewMatchers.isRoot()
                override fun getDescription(): String = "wait for $2 seconds"
                override fun perform(uiController: UiController, v: View?) {
                    uiController.loopMainThreadForAtLeast(2000)
                }
            }
        }
    }
}