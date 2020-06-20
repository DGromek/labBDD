package edu.iis.mto.bdd.trains.services;

import edu.iis.mto.bdd.trains.model.Line;

import org.joda.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class InterinaryServiceImplementation implements InterinaryService  {

    private TimetableService timetableService;
    private final static int MINUTES_MARGIN = 15;

    public InterinaryServiceImplementation(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @Override
    public List<LocalTime> findNextDepartures(String departure, String destination, LocalTime time) {
        //Checking all lines that connect departure and destination.
        List<Line> lines = timetableService.findLinesThrough(departure, destination);
        List<LocalTime> arrivalTimes;
        List<LocalTime> nextDepartures = new ArrayList<>();

        for (Line line : lines) {
            arrivalTimes = timetableService.findArrivalTimes(line, departure);

            for (LocalTime arrivalTime : arrivalTimes) {
                if (arrivalTime.isAfter(time) && arrivalTime.minusMinutes(MINUTES_MARGIN).isBefore(time)) {
                    nextDepartures.add(arrivalTime);
                }
            }
        }

        return nextDepartures;
    }
}
