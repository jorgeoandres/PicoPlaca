package com.jorgoandres.picoplacatest;

import com.jorgeoandres.picoplaca.PicoPlacaPredictor;
import org.junit.Test;

import java.util.InputMismatchException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class PicoPlacaPredictorTest {
    @Test(expected = InputMismatchException.class)
    public void isAValidCarShouldThrowInputMismatchException() throws InputMismatchException {
        PicoPlacaPredictor tester = new PicoPlacaPredictor();
        tester.isAValidCar("AAAA1210");
    }

    @Test(expected = InputMismatchException.class)
    public void isAValidDateShouldThrowInputMismatchException() throws InputMismatchException {
        PicoPlacaPredictor tester = new PicoPlacaPredictor();
        tester.isAValidDate("12-13-1222");
    }

    @Test(expected = InputMismatchException.class)
    public void isAValidTimeShouldThrowInputMismatchException() {
        PicoPlacaPredictor tester = new PicoPlacaPredictor();
        tester.isAValidTime("aa:12");
    }

    @Test (expected = InputMismatchException.class)
    public void PicoPlacaPredictorBadParametersShouldThrowException(){
        PicoPlacaPredictor tester = new PicoPlacaPredictor("AAAA1210", "12-13-1222","aa:12" );
    }

    @Test
    public void getWeekDayFromDateShouldReturnTheCorrectDayOfWeek(){
        PicoPlacaPredictor tester = new PicoPlacaPredictor("AAA121","24/08/2017","10:32");
        assertEquals("22/08/2017 is Thursday is expected to return 3", 3, tester.getWeekDayFromDate());

        tester = new PicoPlacaPredictor("AAA121","27/08/2017","10:32");
        assertEquals("22/08/2017 is Thursday is expected to return 3", 6, tester.getWeekDayFromDate());

    }

    @Test
    public void getPredictionShouldReturnFalse(){
        PicoPlacaPredictor tester = new PicoPlacaPredictor("AAA120","24/08/2017","09:32");
        assertEquals("AAA120 can't be driven on 24/08/2017 at 09:32", false, tester.getPrediction());
        tester = new PicoPlacaPredictor("AAA127","07/09/2017","10:32");
        assertEquals("AAA127 can't be driven on 07/09/2017 at 10:32", false, tester.getPrediction());
        tester = new PicoPlacaPredictor("AAA127","07/09/2017","20:32");
        assertEquals("AAA127 can't be driven on 07/09/2017 at 08:32", false, tester.getPrediction());
        tester = new PicoPlacaPredictor("AAA120","24/08/2017","16:32");
        assertEquals("AAA120 can't be driven on 24/08/2017 at 09:32", false, tester.getPrediction());
        tester = new PicoPlacaPredictor("AAA129","24/08/2017","16:32");
        assertEquals("AAA128 can't be driven on 24/08/2017 at 09:32", false, tester.getPrediction());
        //Saturday
        tester = new PicoPlacaPredictor("AAA120","26/08/2017","16:32");
        assertEquals("AAA120 can't be driven on 26/08/2017 at 09:32", false, tester.getPrediction());
        //Sunday
        tester = new PicoPlacaPredictor("AAA128","27/08/2017","16:32");
        assertEquals("AAA128 can't be driven on 27/08/2017 at 09:32", false, tester.getPrediction());
    }

    @Test
    public void getPredictionShouldReturnTrue(){
        PicoPlacaPredictor tester = new PicoPlacaPredictor("AAA127","24/08/2017","09:00");
        assertEquals("AAA127 can't be driven on 24/08/2017 at 09:00", true, tester.getPrediction());
        tester = new PicoPlacaPredictor("AAA127","07/09/2017","17:000");
        assertEquals("AAA127 can't be driven on 07/09/2017 at 17:00", true, tester.getPrediction());
        tester = new PicoPlacaPredictor("AAA127","07/09/2017","08:34");
        assertEquals("AAA127 can't be driven on 07/09/2017 at 08:34", true, tester.getPrediction());
        //monday 24 Apr.
        tester = new PicoPlacaPredictor("AAA121","24/04/2017","08:34");
        assertEquals("AAA121 can't be driven on 24/04/2017 at 08:34", true, tester.getPrediction());
    }
}