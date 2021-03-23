package ua.com.alevel.view;

import ua.com.alevel.controller.GameOfLifeController;

import java.util.Scanner;

public class GameOfLifeView {
    private final GameOfLifeController gameOfLifeController= new GameOfLifeController();

    public void run() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("This is a task of level 3 called The Game Of Life");
        System.out.println("Please select an option: ");
        System.out.println("1 - play the game");
        System.out.println("0 - exit");
        gameOfLifeController.run(scanner.nextLine());
    }
}
