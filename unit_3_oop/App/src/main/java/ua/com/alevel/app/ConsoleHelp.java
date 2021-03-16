package ua.com.alevel.app;

import ua.com.alevel.service.*;
import ua.com.alevel.service.factory.ConsoleFactory;
import ua.com.alevel.service.impl.ConsoleHelper;

public class ConsoleHelp {
    private final ConsoleHelperService chs = ConsoleFactory.getInstance().getConsoleHelperService();

    public void run() {
        chs.printData("class");
        chs.printData("ConsoleHelper");
        chs.printLineData("is working.");
        chs.printData("Input something:");
        String input = chs.scanLineInputData();
        chs.printData("Your input is:");
        chs.printData(input);
    }
}
