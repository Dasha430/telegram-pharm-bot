package ua.com.alevel.controller;

import ua.com.alevel.entity.CalendarDate;
import ua.com.alevel.service.impl.CalendarImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CalendarController {

    private final CalendarImpl ci = new CalendarImpl();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public void run(String option)  {
        boolean noErrors = true;

        switch (option) {
            case "0":
                System.exit(0);
            case "1":
                do {
                    System.out.println("Choose date format");
                    printAllFormats();
                    try {
                        String format = chooseFormat(reader.readLine());
                        System.out.println("Enter first date");
                        CalendarDate date1 = ci.convertToDate(reader.readLine(), format);
                        System.out.println("Enter second date");
                        CalendarDate date2 = ci.convertToDate(reader.readLine(), format);
                        /*CalendarDate result = ci.findDifference(date1, date2);
                        System.out.println("The difference is " + result.getCentury() + " centuries "
                                + result.getYear() + " years " + result.getMonth() + " months "
                                + result.getDay() + " days " + result.getHours() + " hours " + result.getMinutes() + " minutes "
                            + result.getSeconds() + " seconds " + result.getMilliseconds() + " milliseconds");*/


                    } catch(RuntimeException e) {
                        System.out.println(e.getMessage());
                        noErrors = false;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        noErrors = false;
                    }
                } while (!noErrors);


                break;
        }
    }

    private void printAllFormats() {
        System.out.println("1 - dd/mm/yy, example: 01/12/21 ");
        System.out.println("2 - m/d/yyyy, example: 3/4/2021 ");
        System.out.println("3 - mmm-d-yy, example: March 4 21 ");
        System.out.println("4 - dd-mmm-yyyy 00:00, example: 09 April 789 45:23 ");

    }

    private String chooseFormat(String f) {

        switch (f) {
            case "1":
                return "dd/mm/yy";
            case "2":
                return "m/d/yyyy";
            case "3":
                return "mmm-d-yy";
            case "4":
                return "dd-mmm-yyyy 00:00";
            default:
                throw new RuntimeException("Wrong format");
        }
    }

}
