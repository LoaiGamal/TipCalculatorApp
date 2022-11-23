package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import androidx.test.espresso.assertion.ViewAssertions.matches
import org.hamcrest.Matchers.containsString

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.example.tiptime", appContext.packageName)
//    }

    @Test
    fun calculate_20_percent_tip(){
        onView(ViewMatchers.withId(R.id.costET))
            .perform(typeText("50.00"))

        onView(ViewMatchers.withId(R.id.optionTwentyPercent))
            .perform(click())

        onView(ViewMatchers.withId(R.id.calculateBTN))
            .perform(click())

        onView(ViewMatchers.withId(R.id.resultTV))
            .check(matches(ViewMatchers.withText(containsString("10.00"))))
    }
}