package ua.com.alevel;

import ua.com.alevel.view.AllModuleTasksView;

import java.io.IOException;
import java.util.Scanner;

public class AllModuleTasksMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AllModuleTasksView view = new AllModuleTasksView();

        try {
            view.run();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.next();
    }
}
