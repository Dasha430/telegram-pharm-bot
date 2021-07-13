package ua.com.alevel.service.impl;

import ua.com.alevel.service.ConsoleHelperService;

import java.util.Scanner;

public class ConsoleHelper implements ConsoleHelperService {
    @Override
    public void printData(Object data) {
        System.out.print(data + " ");
    }

    @Override
    public void printLineData(Object data) {
        System.out.println(data + " ");
    }

    @Override
    public String scanLineInputData() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();

    }

    @Override
    public String scanNumberData() {
        Scanner sc = new Scanner(System.in);
        boolean isNumber = true;
        String result = "";
        do {
            isNumber = true;
            result = sc.next();
            for (int i = 0; i < result.length(); i++) {
                if (!Character.isDigit(result.charAt(i))) {
                    System.out.println("It must be an integer. Try again");
                    isNumber = false;
                    break;
                }
            }
        }while (!isNumber);
        return result;
    }
}
