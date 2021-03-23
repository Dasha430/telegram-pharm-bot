package ua.com.alevel;

import ua.com.alevel.controller.StartAllTasks;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StartAllTasks startAllTasks = new StartAllTasks();
        System.out.println("Select which level you would like to check:");
        System.out.println("1 - level 1");
        System.out.println("2 - level 2");
        System.out.println("3 - level 3");
        System.out.println("0 - exit");
        startAllTasks.run(scanner.nextLine());
    }
}
