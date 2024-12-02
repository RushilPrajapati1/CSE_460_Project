package AvailabilityDemand.Tests;

import AvailabilityDemand.AvailabilityDemand;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AvailabilityDemandTest {

    private static AvailabilityDemand availabilityDemand;

    // Create AvailabilityDemand object to test with
    @BeforeClass
    public static void setupAvailabilityDemand() {
        availabilityDemand = new AvailabilityDemand();
    }

    // Reset the availabilityDemand object every time a test finishes so that it can accept a new batch of commands
    @After
    public void resetAvailabilityDemand() {
        availabilityDemand.reset();
    }

    @Test
    // Test 1: Normal, Multiple combinations of published, subscribed, and unsubscribed
    public void testMultipleSubUnsubPublishForValidInputsCaseOne() {
        // Expected output
        List<String> expected = new ArrayList<>(Arrays.asList(
                "John Doe notified of rental car availability in Phoenix from 12/01/2024 to 12/05/2024 by Turo rental car service",
                "John Doe notified of rental car availability in Phoenix from 11/30/2024 to 12/09/2024 by Enterprise rental car service",
                "Jane Doe notified of rental car availability in Phoenix from 11/30/2024 to 12/09/2024 by Enterprise rental car service",
                "Richard notified of rental car availability in Phoenix from 12/01/2024 to 12/05/2024 by Turo rental car service",
                "Richard notified of rental car availability in Phoenix from 11/30/2024 to 12/09/2024 by Enterprise rental car service",
                "William notified of rental car availability in Phoenix from 12/10/2024 to 12/15/2024 by Enterprise rental car service"
        ));
        // Feed the availabilityDemand object with some commands
        availabilityDemand.processInput("publish, Turo, Phoenix, 12/01/2024, 12/05/2024");
        availabilityDemand.processInput("publish, Enterprise, Phoenix, 11/30/2024, 12/09/2024");
        //john should get notified by both providers
        availabilityDemand.processInput("subscribe, John Doe, Phoenix, 12/01/2024, 12/05/2024");
        //William will not get notified as no providers match the criteria
        availabilityDemand.processInput("subscribe, William, Phoenix, 12/10/2024, 12/15/2024");
        //jane should get notified by Enterprise as he matches the criteria
        availabilityDemand.processInput("subscribe, Jane Doe, Phoenix, 12/06/2024, 12/09/2024");
        //one subscriber removed from system
        availabilityDemand.processInput("unsubscribe, John Doe, Phoenix, 12/01/2024, 12/05/2024");
        //richard should get notified by both providers
        availabilityDemand.processInput("subscribe, Richard, Phoenix, 12/01/2024, 12/04/2024");
        //one subscriber removed from system
        availabilityDemand.processInput("unsubscribe, Jane Doe, Phoenix, 12/06/2024, 12/09/2024");
        //no notification as no one matches the criteria
        availabilityDemand.processInput("publish, Turo, Phoenix, 12/06/2024, 12/10/2024");
        //only william will get the notification
        availabilityDemand.processInput("publish, Enterprise, Phoenix, 12/10/2024, 12/15/2024");

        // Obtain the actual result from your availabilityDemand object and compare it with the expected output
        List<String> actual = availabilityDemand.getAggregatedOutput().stream()
                .map(String::strip)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        expected =  expected.stream().map(String :: toLowerCase).collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    @Test
    // Test 2: Abnormal, Published dates from the same provider for the same location cannot be the same
    public void testDateConstraintsOnProvider() {
        // Expected output
        List<String> expected = new ArrayList<>(Arrays.asList(
                "John Doe notified of rental car availability in Phoenix from 11/30/2024 to 12/05/2024 by Turo rental car service",
                "Jane Doe notified of rental car availability in Phoenix from 12/10/2024 to 12/15/2024 by Turo rental car service"
        ));
        // Feed the availabilityDemand object with some commands
        availabilityDemand.processInput("subscribe, John Doe, Phoenix, 12/01/2024, 12/05/2024");
        availabilityDemand.processInput("subscribe, Jane Doe, Phoenix, 12/10/2024, 12/15/2024");
        availabilityDemand.processInput("publish, Turo, Phoenix, 11/30/2024, 12/05/2024");
        //duplicate with the first published availability, should be discarded
        availabilityDemand.processInput("publish, Turo, Phoenix, 11/30/2024, 12/05/2024");

        availabilityDemand.processInput("publish, Turo, Phoenix, 12/10/2024, 12/15/2024");
        // Obtain the actual result from your availabilityDemand object and compare it with the expected output
        List<String> actual = availabilityDemand.getAggregatedOutput().stream()
                .map(String::strip)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        expected =  expected.stream().map(String :: toLowerCase).collect(Collectors.toList());

        assertEquals(expected, actual);
    }
}