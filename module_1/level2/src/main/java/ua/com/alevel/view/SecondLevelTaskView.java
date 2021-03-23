package ua.com.alevel.view;

import ua.com.alevel.controller.SecondLevelTaskController;

import java.util.Scanner;

public class SecondLevelTaskView {
    private SecondLevelTaskController slc = new SecondLevelTaskController();

    public void run() {

        Scanner scanner = new Scanner((System.in));
        System.out.println("This is a task of level 2.");
        System.out.println("Please select option:");
        System.out.println("1 - check if your input string that contains different brackets and braces is correct");
        System.out.println("0 - exit");
        slc.run(scanner.nextLine());

    }
}
