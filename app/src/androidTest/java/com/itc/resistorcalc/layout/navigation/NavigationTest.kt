package com.itc.resistorcalc.layout.navigation

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import com.itc.resistorcalc.R
import com.itc.resistorcalc.view.InitialFragment
import com.itc.resistorcalc.view.resistorcalc.ResistorCalcFragment
import org.hamcrest.Matchers
import com.google.common.truth.Truth.assertThat
import org.junit.Test

@MediumTest
class NavigationTest {

    @Test
    fun detailsButton_clicked_navigateToDetailsFragment(){
        val scenario = launchFragmentInContainer<ResistorCalcFragment>(Bundle(), R.style.Theme_ResistorCalc)

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        scenario.onFragment{
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(R.id.resistorCalcFragment)
            Navigation.setViewNavController(it.requireView(), navController)
        }

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
            Matchers.allOf(
                isDescendantOfA(withId(R.id.erv_label)),
                withClassName(Matchers.endsWith("EditText"))
            )
        ).perform(typeText("1234"))

        onView(withId(R.id.details_button)).check(matches(isEnabled())).perform(
            closeSoftKeyboard(),
            scrollTo(),
            click()
        )
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.resistorDetailsFragment)
    }

    @Test
    fun resCalcColorBtn_clicked_goToResistorCalculator(){
        val scenario = launchFragmentInContainer<InitialFragment>(Bundle(), R.style.Theme_ResistorCalc)

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        scenario.onFragment{
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(it.requireView(), navController)
        }

        onView(withId(R.id.resCalcColorBtn)).perform(click())

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.resistorCalcFragment)
    }

    @Test
    fun resCalcValueBtn_clicked_goToFromValueToColorCalc(){
        val scenario = launchFragmentInContainer<InitialFragment>(Bundle(), R.style.Theme_ResistorCalc)

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        scenario.onFragment{
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(it.requireView(), navController)
        }

        onView(withId(R.id.resCalcValueBtn)).perform(click())

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.fromValueResistorFragment)
    }
}