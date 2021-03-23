package ua.com.alevel.view;

import ua.com.alevel.controller.FirstLevelTasksController;
import java.util.Scanner;

public class FirstLevelTasksView {

    private FirstLevelTasksController controller = new FirstLevelTasksController();

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("These are tasks of level 1.");
        System.out.println("Please select task number: ");
        System.out.println("1 - find number of unique numbers in array");
        System.out.println("2 - check if it's possible to move the knight to the specified position on a limitless chess board");
        System.out.println("3 - calculate the area of a triangle");
        System.out.println("0 - exit");
        controller.run(scanner.nextLine());
    }
}
