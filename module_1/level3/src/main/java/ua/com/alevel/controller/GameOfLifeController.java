package ua.com.alevel.controller;

import ua.com.alevel.service.GameOfLifeService;
import ua.com.alevel.service.factory.GameOfLifeFactory;

import java.util.Scanner;

public class GameOfLifeController {
    private final GameOfLifeService gameOfLifeService = GameOfLifeFactory.getInstance().getGameOfLifeService();

    public final void run(String option) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            switch (option) {
                case "0":
                    return;
                case "1":
                    System.out.println("Enter the height of the game board:");
                    gameOfLifeService.setHeight(getIntValue(scanner.nextLine()));
                    System.out.println("Enter the width of the game board:");
                    gameOfLifeService.setWidth(getIntValue(scanner.nextLine()));
                    gameOfLifeService.fillBoard();
                    gameOfLifeService.printBoard();
                    System.out.println("Enter how many alive cells would you like to make:");
                    int alive = getIntValue(scanner.nextLine());
                    System.out.println("Enter the coordinates of alive cells(format: row column).");
                    for (int i = 0; i < alive; i++) {
                        System.out.print(i + 1 + ": ");
                        int[] coordinates = getCoordinates(scanner.nextLine());
                        gameOfLifeService.setAlive(coordinates[0], coordinates[1] );
                        System.out.println();
                    }
                    System.out.println("Initial board:");
                    gameOfLifeService.printBoard();
                    boolean gameOn = true;
                    while (gameOn) {
                        System.out.println("Enter 0 to stop playing or 1 to make a step:");
                        String gameOption = scanner.nextLine();
                        switch (gameOption) {
                            case "1":
                                gameOfLifeService.step();
                                gameOfLifeService.printBoard();
                                break;
                            case "0":
                                gameOn = false;
                                break;
                            default:
                                System.out.println("Wrong index");
                        }

                    }
                    break;
                default:
                    System.out.println("Wrong index");
                    break;
            }
            System.out.println("Enter 0 to exit or 1 to start a new game");
            option = scanner.nextLine();
        }
    }
    public static int getIntValue(String s) {
        String newS = s.trim();
        try {
            for (int i = 0; i < s.length(); i++) {
                if (!Character.isDigit(newS.charAt(i))) {
                    throw new RuntimeException("the value should be an integer");
                }
            }
            return Integer.parseInt(newS);

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        throw new RuntimeException();
    }
    public static int[] getCoordinates(String s) {
        int[] coordinates = new int[2];
        String[] sCoordinates = s.split(" ");
        for (int i = 0; i < 2; i++) {
            coordinates[i] = getIntValue(sCoordinates[i]);
        }
        return coordinates;

    }
}
