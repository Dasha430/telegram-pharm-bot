package ua.com.alevel.controller;

import ua.com.alevel.util.ArrayTaskUtil;
import ua.com.alevel.util.ChessTaskUtil;
import ua.com.alevel.util.TriangleAreaUtil;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class FirstLevelTasksController {
    public void run(String task) {
        Scanner scanner = new Scanner(System.in);

        while(true) {

            switch(task) {
                case "0":
                    return;
                case "1":
                    System.out.println("Enter your array of integers: ");
                    List<Integer>array = convertArrayToInt(makeArray(scanner.nextLine()));
                    System.out.println(ArrayTaskUtil.uniqueNumbersCount(array));
                    break;
                case "2":
                    System.out.println("Enter the current position of the knight(position format: rowNumber columnNumber):");
                    List<Integer> currentPosition = convertArrayToInt(makeArray(scanner.nextLine()));
                    System.out.println("Enter the next position");
                    List<Integer> nextPosition = convertArrayToInt(makeArray(scanner.nextLine()));
                    if (ChessTaskUtil.checkSfPossible(currentPosition, nextPosition)) {

                        System.out.println("This position is possible");
                    } else {
                        System.out.println("This position is impossible");
                    }
                    break;
                case "3":
                    System.out.println("Enter coordinates of the first triangle top (example: 1 3):");
                    List<Double> topA = convertArrayToDouble(makeArray(scanner.nextLine()));
                    System.out.println("Enter coordinates of the second triangle top:");
                    List<Double> topB = convertArrayToDouble(makeArray(scanner.nextLine()));
                    System.out.println("Enter coordinates of the third triangle top:");
                    List<Double> topC = convertArrayToDouble(makeArray(scanner.nextLine()));
                    System.out.println("The area of the triangle is: " + TriangleAreaUtil.CalculateArea(topA, topB, topC));
                    break;
                default:
                    System.out.println("Wrong option");

            }

            System.out.println("Press 0 to exit or repeat the logic");
            task = scanner.nextLine();

        }

    }
    public static List<String> makeArray(String strArray) {
        List<String> array = new ArrayList<>();
        String regex = "[0-9]+";
        try {
            String[] numbers = strArray.trim().split(" ");
            for (String n : numbers) {
                if (!n.matches(regex)) {
                    throw new RuntimeException("input must only contain integer values");
                }
                array.add(n);
            }
            return array;
        }catch(RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }
    public static List<Integer> convertArrayToInt( List<String> array) {
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < array.size(); i++) {
            result.add(Integer.parseInt(array.get(i)));
        }
        return result;
    }
    public static List<Double> convertArrayToDouble( List<String> array) {
        List<Double> result = new ArrayList<>();
        for(int i = 0; i < array.size(); i++) {
            result.add(Double.parseDouble(array.get(i)));
        }
        return result;
    }
}
