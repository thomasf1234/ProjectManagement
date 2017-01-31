package com.abstractx1.projectmanagement;

/**
 * Created by tfisher on 31/01/2017.
 */

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import org.junit.Before;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class BaseInstrumentedTest {
    @Before
    public void setUpDeleteDatabase() throws Exception {
        //Context appContext = InstrumentationRegistry.getTargetContext();
    }

    @org.junit.Test
    public void testAnswerQuestion() throws InterruptedException {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        assertThat(device, notNullValue());
        device.pressHome();
    }
}
