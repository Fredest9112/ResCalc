package com.itc.resistorcalc.view.resistorcalc

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import com.itc.resistorcalc.R
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.endsWith
import org.junit.Test
import com.google.common.truth.Truth.assertThat

@MediumTest
class ResistorCalcFragTest {

    @Test
    fun detailsButton_screenWithValidDetails_isEnabled(){
        val scenario = launchFragmentInContainer<ResistorCalcFragment>(Bundle(), R.style.Theme_ResistorCalc)

        scenario.onFragment{}

        onView(withId(R.id.color1_selection)).perform(click())
        onView(withText("Rojo"))
            .inRoot(RootMatchers.isPlatformPopup())
            .perform(click())

        onView(withId(R.id.color2_selection)).perform(click())
        onView(withText("Rojo"))
            .inRoot(RootMatchers.isPlatformPopup())
            .perform(click())

        onView(withId(R.id.multiplier_selection)).perform(click())
        onView(withText("Rojo"))
            .inRoot(RootMatchers.isPlatformPopup())
            .perform(click())

        onView(withId(R.id.tolerance_selection)).perform(click())
        onView(withText("Rojo"))
            .inRoot(RootMatchers.isPlatformPopup())
            .perform(click())

        onView(
            allOf(
                isDescendantOfA(withId(R.id.erv_label)),
                withClassName(endsWith("EditText"))
            )
        ).perform(typeText("1234"))

        onView(withId(R.id.details_button))
            .check(matches(isEnabled()))
    }
}