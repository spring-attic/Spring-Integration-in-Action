package com.manning.siia.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 */
@ContextConfiguration("classpath:TEST-flight-notifications.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class FlightNotificationsTest {

    @Test
    public void shouldLoadContext() {
        //spring does the job
    }
}
