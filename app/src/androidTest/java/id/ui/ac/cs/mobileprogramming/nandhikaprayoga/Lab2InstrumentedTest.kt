package id.ui.ac.cs.mobileprogramming.nandhikaprayoga

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import id.ui.ac.cs.mobileprogramming.nandhikaprayoga.activities.Lab2Activity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withText


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class Lab2InstrumentedTest {
    @Rule
    @JvmField
    var testRule =
        ActivityTestRule(Lab2Activity::class.java, true, false)

    @Before
    fun setUp() {
        val intent = Intent()
        testRule.launchActivity(intent)
    }

    @Test
    fun performReverseText() {
        val original = "nandhika"
        val reversed = "akihdnan"

        onView(withId(R.id.lab2Input)).perform(typeText(original))
        onView(withId(R.id.lab2Enter)).perform(click())
        onView(withId(R.id.lab2Output)).check(ViewAssertions.matches(withText(reversed)))
    }
}