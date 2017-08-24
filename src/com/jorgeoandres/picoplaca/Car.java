
package com.jorgeoandres.picoplaca;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * This class represents a Car which has a licence plate number and will be Evaluated on the "Pico y Placa Predictor".
 *
 * @autor Jorge Orozco
 * @version 1.0
 * @since 2017-08-24
 */
public class Car {
    /**
     * The car's licence plate number
     */
    String licencePlateNumber;

    public Car(){
        this.licencePlateNumber="";
    }
    /**
     * Constructor with a string that represents a licence plate number. It will throw an InputMismatchException if the licence plate number is not valid
     * @param licencePlateNumber string A valid licence plate number
     */
    public Car(String licencePlateNumber){
        if(validateLicencePlateNumber(licencePlateNumber)==true)
            this.licencePlateNumber=licencePlateNumber;
        else
            throw new InputMismatchException("The string is not a valid licence plate number");
    }

    /**
     * Sets the car's licence plate number. It will throw an InputMismatchException if the licence plate number is not valid
     * @param licencePlateNumber licencePlateNumber
     */
    public void setLicencePlateNumber(String licencePlateNumber) {
        if(validateLicencePlateNumber(licencePlateNumber)==true)
            this.licencePlateNumber=licencePlateNumber;
        else
            throw new InputMismatchException("The string is not a valid licence plate number");
    }

    /**
     * Gets the car's licence plate number
     * @return String Licence plate number
     */
    public String getLicencePlateNumber(){
        return this.licencePlateNumber;
    }

    /**
     * Gets the last char of the licence plate number, it always has to be a number
     * @return int last digit of licence plate number
     */
    public int getLastDigitOfLicencePlateNumber(){
        return  Integer.parseInt(this.licencePlateNumber.substring(this.licencePlateNumber.length()-1, this.licencePlateNumber.length()));
    }

    /**
     * Provides an evaluation for the provided licence plate number. It evaluates the string by matching it with a regular expression.
     * The success cases will be:
     * AAA1212
     * AAA232
     * The both cases start with 3 letters. The first one could be followed by three digits and the following by four digits. Every thing else will be refused.
     * @param licencePlateNumber string the provided licence plate number
     * @return boolean Return true if is a valid licence plate number
     */
    public boolean validateLicencePlateNumber(String licencePlateNumber){
        String regularExpression="[A-Za-z]{3}\\d{3,4}";
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(licencePlateNumber);
        return matcher.matches();
    }
}
