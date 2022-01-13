package eu.trackyourtasks.trackyourtasks;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
// @Mateusz Ostapko
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityRule = new ActivityScenarioRule(MainActivity.class);

    @Test
    public void test() {
        mActivityRule.getScenario().onActivity(activity -> {
            assertNotNull(activity);
        });
    }
}