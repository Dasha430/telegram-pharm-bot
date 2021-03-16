package ua.com.alevel;

import ua.com.alevel.app.Calculate;
import ua.com.alevel.app.ConsoleHelp;

public class Main {
    public static void main(String[] args) {
        ConsoleHelp ch = new ConsoleHelp();
        ch.run();
        Calculate calc = new Calculate();
        calc.run();
    }


}
