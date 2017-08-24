package com.jorgeoandres.picoplaca;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;

/**
 * This class provides an implementation for a PicoPlacaPredictor.
 * Pico y Place here in Quito has the following schedules
 *      Monday: Licence plate numbers which finish with number 1-2
 *      Tuesday: Licence plate numbers which finish with number 3-4
 *      Wednesday: Licence plate numbers which finish with number 5-6
 *      Thrusday: Licence plate numbers which finish with number 7-8
 *      Friday: Licence plate numbers which finish with number 9-0
 * On all these days the restriction schedule is from 07:00 to 09:30 in the morning and in the afternoon the schedule is from 16:00 to 19:30
 *
 * This class will have a Car Object, a date and a Time, after process all the inputs, it can give a prediction whether you can use the car or not
 */
public class PicoPlacaPredictor {

    Car car;
    Date dateToBePredicted;
    Date timeToBePredicted;
    String[] schedule = new String[]{"12","34","56","78","90", "aa", "aa"};
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    Date initTimeMorningPicoPlaca;
    Date finalTimeMorningPicoPlaca;
    Date initTimeAfternoonPicoPlaca;
    Date finalTimeAfternoonPicoPlaca;

    Calendar calendar;

    /**
     * Creates a PicoPlacaPredictor
     */
    public PicoPlacaPredictor(){
        this.car=null;
        this.dateToBePredicted=null;
        this.timeToBePredicted=null;
        try {
            initTimeMorningPicoPlaca=timeFormat.parse("07:00");
            finalTimeMorningPicoPlaca=timeFormat.parse("09:30");
            initTimeAfternoonPicoPlaca=timeFormat.parse("16:00");
            finalTimeAfternoonPicoPlaca=timeFormat.parse("19:30");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a PicoPlacaPredictor with the specified licence plate number, date and time
     * @param licencePlateNumber String Licence plate number (Ex: AAA1231, AAA123)
     * @param date String Date in dd/MM/yyyy format
     * @param time String Time in HH/mm format
     * @throws InputMismatchException when the parameters are not in the specified format. The message of the exception shows which parameter failed
     */
    public  PicoPlacaPredictor(String licencePlateNumber, String date, String time) throws InputMismatchException {
            isAValidCar(licencePlateNumber);
            isAValidDate(date);
            isAValidTime(time);

        try {
            initTimeMorningPicoPlaca=timeFormat.parse("07:00");
            finalTimeMorningPicoPlaca=timeFormat.parse("09:30");
            initTimeAfternoonPicoPlaca=timeFormat.parse("16:00");
            finalTimeAfternoonPicoPlaca=timeFormat.parse("19:30");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /**
     * Set car
     * @param car Car
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Set date
     * @param dateToBePredicted Date
     */
    public void setDateToBePredicted(Date dateToBePredicted) {
        this.dateToBePredicted = dateToBePredicted;
    }

    /**
     * Set time
     * @param timeToBePredicted Date
     */
    public void setTimeToBePredicted(Date timeToBePredicted) {
        this.timeToBePredicted = timeToBePredicted;
    }

    /**
     * Gets the car
     * @return Car car
     */
    public Car getCar() {
        return car;
    }

    /**
     * Gets the date
     * @return Date date
     */
    public Date getDateToBePredicted() {
        return dateToBePredicted;
    }

    /**
     * Gets the time
     * @return Date time
     */
    public Date getTimeToBePredicted() {
        return timeToBePredicted;
    }

    /**
     * Uses the Car constructor to validate the licencePlate number
     * @param licencePlateNumber String licence plate number
     * @return boolean if is a valid licence plate number
     * @throws InputMismatchException
     */
    public boolean isAValidCar(String licencePlateNumber) throws InputMismatchException{
            Car newCar = new Car(licencePlateNumber);
            this.setCar(newCar);
            return true;
    }

    /**
     * Uses a date format to parse string. The correct format to be a valid date is dd/MM/yyyy
     * @param dateAsString String Date as string
     * @return boolean True if is a valid date
     */
    public boolean isAValidDate(String dateAsString){
        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date newDate;
        try {
            newDate = sourceFormat.parse(dateAsString);
        } catch (ParseException e) {
            throw new InputMismatchException("The string is not a valid date. You should enter dd/MM/yyyy");
        }
        setDateToBePredicted(newDate);
        return true;
    }

    /**
     * User a date format to parse string. The correct format to be a valid time is HH:mm
     * @param timeAsString String Time as string
     * @return boolean True if is a valid Time
     */
    public boolean isAValidTime(String timeAsString){
        DateFormat sourceFormat = new SimpleDateFormat("HH:mm");
        Date newDate;
        Calendar calendar;
        try {
            newDate = sourceFormat.parse(timeAsString);

        } catch (ParseException e) {
            throw new InputMismatchException("The string is not a valid time. You should enter HH:mm");
        }
        this.setTimeToBePredicted(newDate);
        return true;
    }

    /**
     * Gets whether you can drive your car at the given conditions or not
     * @return boolean True if you can't drive (You have Pico Placa)
     */
    public boolean getPrediction(){
        int weekDay = this.getWeekDayFromDate();
        int lastDigitOfLicencePlateNumber= this.car.getLastDigitOfLicencePlateNumber();
        if(this.schedule[weekDay].contains(""+lastDigitOfLicencePlateNumber)==false)
            return false;
        if(this.isBetweenHours()==false)
            return false;
        return true;
    }

    /**
     * Gets the number of the week of dateToBePredicted date. It goes from 0 to 6
     * @return
     */
    public int getWeekDayFromDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.dateToBePredicted);
        return calendar.get(Calendar.DAY_OF_WEEK)-2 == -1 ? 6 : calendar.get(Calendar.DAY_OF_WEEK)-2;
    }

    /**
     * Gets whether the given hour is between Pico Placa schedule or not
     * @return boolean True if the hour is between 07:00 to 09:30 and 16:00 to 19:30
     */
    public boolean isBetweenHours(){
        return (!initTimeMorningPicoPlaca.after(this.timeToBePredicted) && !finalTimeMorningPicoPlaca.before(this.timeToBePredicted)) || (!initTimeAfternoonPicoPlaca.after(this.timeToBePredicted) && !finalTimeAfternoonPicoPlaca.before(this.timeToBePredicted));
    }
}
