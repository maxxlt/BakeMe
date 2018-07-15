package ru.maxxlt.bakeme;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FragmentTest {

    @Rule
    public ActivityTestRule<HostActivity> hostActivityTestRule = new ActivityTestRule<>(HostActivity.class);
    @Before
    public void init(){
        hostActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
    }
}
