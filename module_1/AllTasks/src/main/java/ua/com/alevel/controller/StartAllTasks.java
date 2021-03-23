package ua.com.alevel.controller;

import ua.com.alevel.view.FirstLevelTasksView;
import ua.com.alevel.view.SecondLevelTaskView;
import ua.com.alevel.view.GameOfLifeView;

import java.util.Scanner;

public class StartAllTasks {

    public static void run(String option) {
        Scanner scanner = new Scanner(System.in);

        FirstLevelTasksView firstLevel = new FirstLevelTasksView();
        SecondLevelTaskView secondLevel = new SecondLevelTaskView();
        GameOfLifeView gameOfLife = new GameOfLifeView();


        while (true) {
            switch (option) {
                case "0":
                    System.exit(0);
                case"1":
                   firstLevel.run();
                   break;
                case "2":
                    secondLevel.run();
                    break;
                case "3":
                     gameOfLife.run();
                     break;
                default:
                    System.out.println("Wrong option");
                    break;
            }
            System.out.println("Enter 0 to exit or select next level:");
            option = scanner.nextLine();

        }



    }
}
