package ua.com.alevel.controller;

import ua.com.alevel.util.ParenthesesTaskUtil;

import java.util.Scanner;

public class SecondLevelTaskController {


    public void run(String option) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            switch(option) {
                case "0":
                    return;
                case "1":
                    System.out.println("Enter a string that contains parenthesis: ");
                    if (ParenthesesTaskUtil.isAcceptable(scanner.nextLine())) {
                        System.out.println("This string is possible");
                    } else {
                        System.out.println("This string is impossible");
                    }
                    break;
            }
            System.out.println("Press 0 to exit or 1 to check the string again");
            option = scanner.nextLine();
        }


    }
}
