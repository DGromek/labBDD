package edu.iis.mto.bdd.trains.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.I;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;
import org.joda.time.LocalTime;

import java.util.List;

public class OptimalItinerarySteps {

    //Scenario 1
    @Zakładając("^pociągi linii \"(.*)\" z \"(.*)\" odjeżdżają ze stacji \"(.*)\" do \"(.*)\" o$")
    public void givenArrivingTrains(String line, String lineStart, String departure, String destination,
            @Transform(JodaLocalTimeConverter.class) List<LocalTime> departureTimes) {
        throw new PendingException();

    }

    @Gdy("^chcę podróżować z \"([^\"]*)\" do \"([^\"]*)\" o (.*)$")
    public void whenIWantToTravel(String departure, String destination, @Transform(JodaLocalTimeConverter.class) LocalTime startTime) {
        throw new PendingException();
    }

    @Wtedy("^powinienem uzyskać informację o pociągach o:$")
    public void shouldBeInformedAbout(@Transform(JodaLocalTimeConverter.class) List<LocalTime> expectedTrainTimes) {
        throw new PendingException();
    }

    //Scenario 2
    @Zakładając("^chcę się dostać z \"([^\"]*)\" do \"([^\"]*)\"$")
    public void chcęSięDostaćZDo(String departure, String destination) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @I("^następny pociąg odjeżdża o (.*) na linii \"([^\"]*)\"$")
    public void następnyPociągOdjeżdżaONaLinii(@Transform(JodaLocalTimeConverter.class) LocalTime departureTime, String line)
            throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Gdy("^zapytam o godzinę przyjazdu$")
    public void zapytamOGodzinęPrzyjazdu() {
    }

    @Wtedy("^powinienem uzyskać następujący szacowany czas przyjazdu: (.*)$")
    public void powinienemUzyskaćNastępującySzacowanyCzasPrzyjazdu(@Transform(JodaLocalTimeConverter.class) LocalTime arrivalTime) {
    }
}
