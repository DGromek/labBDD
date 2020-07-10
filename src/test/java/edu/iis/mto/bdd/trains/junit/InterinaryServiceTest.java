package edu.iis.mto.bdd.trains.junit;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.InterinaryService;
import edu.iis.mto.bdd.trains.services.InterinaryServiceImplementation;
import edu.iis.mto.bdd.trains.services.TimetableService;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InterinaryServiceTest {

    private String departure = "UNRELEVANT DEPARTURE";
    private String destination = "UNRELEVANT DESTINATION";
    private String[] constStations = {departure, "UNRELEVANT PASSING STATION 1", "UNRELEVANT PASSING STATION 2", destination};

    private Line line = Line.named("TEST LINE")
                            .departingFrom(departure)
                            .withStations(constStations);

    private LocalTime startingTime = new LocalTime("9:00");

    private InterinaryService interinaryService;
    private TimetableService timetableService;

    @Before
    public void setUp() {
        timetableService = mock(TimetableService.class);
        interinaryService = new InterinaryServiceImplementation(timetableService);
        
        when(timetableService.findLinesThrough(departure, destination)).thenReturn(Collections.singletonList(line));
    }

    @Test
    public void shouldReturnNoneDepartureTimes() {
        List<LocalTime> trainsTimes = new ArrayList<>() {{
            add(new LocalTime("3:10"));
            add(new LocalTime("4:20"));
            add(new LocalTime("9:16"));
            add(new LocalTime("9:17"));
            add(new LocalTime("6:00"));
        }};

        when(timetableService.findArrivalTimes(line, departure)).thenReturn(trainsTimes);

        List<LocalTime> result = interinaryService.findNextDepartures(departure, destination, startingTime);

        assertEquals(0, result.size());
    }

    @Test
    public void shouldReturnExpectedResult() {
        List<LocalTime> trainsTimes = new ArrayList<>() {{
            add(new LocalTime("4:10"));
            add(new LocalTime("9:05"));
            add(new LocalTime("9:10"));
            add(new LocalTime("9:50"));
            add(new LocalTime("10:00"));
        }};

        List<LocalTime> expectedResult = new ArrayList<>() {{
            add(new LocalTime("9:05"));
            add(new LocalTime("9:10"));
        }};

        when(timetableService.findArrivalTimes(line, departure)).thenReturn(trainsTimes);

        List<LocalTime> resultTimes = interinaryService.findNextDepartures(departure, destination, startingTime);

        assertEquals(expectedResult, resultTimes);
    }

    @Test
    public void shouldReturnAllDepartureTimes() {
        List<LocalTime> trainsTimes = new ArrayList<>() {{
            add(new LocalTime("9:01"));
            add(new LocalTime("9:02"));
            add(new LocalTime("9:04"));
            add(new LocalTime("9:08"));
            add(new LocalTime("9:12"));
        }};

        when(timetableService.findArrivalTimes(line, departure)).thenReturn(trainsTimes);

        List<LocalTime> resultTimes = interinaryService.findNextDepartures(departure, destination, startingTime);

        assertEquals(trainsTimes, resultTimes);
    }
}
