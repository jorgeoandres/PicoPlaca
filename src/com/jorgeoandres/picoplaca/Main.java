package com.jorgeoandres.picoplaca;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        PicoPlacaPredictor predictor = new PicoPlacaPredictor();
        // write your code here
        if(args.length==3)
        {
            try{
               predictor = new PicoPlacaPredictor(args[0],args[1],args[2]);
               System.out.println("Do I have Pico y Placa? "+predictor.getPrediction());
            }
            catch (InputMismatchException e)
            {
                System.out.println(e.getMessage());
                getInputs();
            }
        }
        else{
            getInputs();
        }

    }

    public static void getInputs(){
        PicoPlacaPredictor tester = new PicoPlacaPredictor();
        Scanner input = new Scanner(System.in);
        boolean validLicence = false;
        boolean validDate = false;
        boolean validTime = false;

        while (validLicence==false)
        {
            try{
                System.out.println("Enter the licence plate number: ");
                String licencePlateNumber = input.next();
                validLicence=tester.isAValidCar(licencePlateNumber);
            }
            catch (InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }

        while (validDate==false)
        {
            try{
                System.out.println("Enter the date in dd/MM/yyyy format: ");
                String date = input.next();
                validDate=tester.isAValidDate(date);
            }
            catch (InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }

        while (validTime==false)
        {
            try{
                System.out.println("Enter the time in HH:mm format: ");
                String time = input.next();
                validTime=tester.isAValidTime(time);
            }
            catch (InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Do I have Pico y Placa? "+tester.getPrediction());
    }
}
