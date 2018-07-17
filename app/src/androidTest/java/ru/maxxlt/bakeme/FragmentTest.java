package ru.maxxlt.bakeme;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.maxxlt.bakeme.test.EspressoIdlingResource;
import ru.maxxlt.bakeme.ui.main.MainFragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class FragmentTest {

    @Rule
    public ActivityTestRule<HostActivity> hostActivityTestRule = new ActivityTestRule<>(HostActivity.class);

    @Before
    public void init() {
        MainFragment mainFragment = new MainFragment();
        hostActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.master_list_fragment,mainFragment);
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    @Test
    public void onFragmentRecipeSelected() throws InterruptedException {
        onView(withId(R.id.main_fragment_recycler)).check(matches(hasDescendant(withText("Nutella Pie"))));
        onView(withId(R.id.main_fragment_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withText("heavy cream(cold)")).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }
}
