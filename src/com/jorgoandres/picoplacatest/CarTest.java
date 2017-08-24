package com.jorgoandres.picoplacatest;

import com.jorgeoandres.picoplaca.Car;
import org.junit.*;

import java.util.InputMismatchException;

import static junit.framework.TestCase.assertEquals;

public class CarTest {
    @Test (expected = InputMismatchException.class)
    public void setLicencePlateNumberWithANotValidLicencePlateNumberShouldThrowException() throws InputMismatchException{
            Car tester = new Car();
            tester.setLicencePlateNumber("AAA12122");
    }

    @Test
    public void getLastDigitOfLicencePlateNumberShouldReturnZero() throws Exception {
        Car tester = new Car("AAA1210");
        assertEquals("AAA1210 licence plate number last digit is 0", 0, tester.getLastDigitOfLicencePlateNumber());
        tester.setLicencePlateNumber("AAA120");
        assertEquals("AAA120 licence plate number last digit is 0", 0, tester.getLastDigitOfLicencePlateNumber());
    }

    @Test
    public void validateAValidLicencePlateNumberShouldReturnTrue() throws Exception {
        Car tester = new Car("AAA1210");
        assertEquals("AAA1210 licence plate number is valid", true, tester.validateLicencePlateNumber(tester.getLicencePlateNumber()));
        tester.setLicencePlateNumber("AAA120");
        assertEquals("AAA120 licence plate number last digit is valid", true, tester.validateLicencePlateNumber(tester.getLicencePlateNumber()));
        tester.setLicencePlateNumber("aaa1210");
        assertEquals("aaa1210 licence plate number is valid", true, tester.validateLicencePlateNumber(tester.getLicencePlateNumber()));
        tester.setLicencePlateNumber("aaa120");
        assertEquals("aaa120 licence plate number last digit is valid", true, tester.validateLicencePlateNumber(tester.getLicencePlateNumber()));
    }

    @Test
    public void validateAnInvalidLicencePlateNumberShouldReturnFalse() throws Exception {
        Car tester = new Car();
        assertEquals("AAAA1210 licence plate number is invalid", false, tester.validateLicencePlateNumber("AAAA1210"));
        assertEquals("AAA120123 licence plate number last digit is invalid", false, tester.validateLicencePlateNumber("AAA120123"));
        assertEquals("AA113 licence plate number last digit is invalid", false, tester.validateLicencePlateNumber("AA113"));
        assertEquals("A1 licence plate number last digit is invalid", false, tester.validateLicencePlateNumber("A1"));
        assertEquals("AA11A licence plate number last digit is invalid", false, tester.validateLicencePlateNumber("AA11A"));
        assertEquals(" licence plate number last digit is invalid", false, tester.validateLicencePlateNumber(""));
        assertEquals("a-a licence plate number last digit is invalid", false, tester.validateLicencePlateNumber("a-a"));
        assertEquals("aaaa1210 licence plate number is invalid", false, tester.validateLicencePlateNumber("aaaa1210"));
        assertEquals("aaa120123 licence plate number last digit is invalid", false, tester.validateLicencePlateNumber("aaa120123"));
    }

}