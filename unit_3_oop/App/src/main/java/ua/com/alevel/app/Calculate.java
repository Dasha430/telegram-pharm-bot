package ua.com.alevel.app;

import ua.com.alevel.service.CalculatorService;
import ua.com.alevel.service.ConsoleHelperService;
import ua.com.alevel.service.factory.CalculatorFactory;
import ua.com.alevel.service.factory.ConsoleFactory;

import java.math.BigInteger;
import java.util.Scanner;

public class Calculate {
    private final CalculatorService calculatorService = CalculatorFactory.getInstance().getCalculatorService();
    private final ConsoleHelperService chs = ConsoleFactory.getInstance().getConsoleHelperService();

    public void run() {
        chs.printLineData("\nEnter 2 integer values: ");
        chs.printData("1: ");
        BigInteger a = new BigInteger(chs.scanNumberData());
        chs.printData("\n2: ");
        BigInteger b = new BigInteger(chs.scanNumberData());

        System.out.println(a + " + " + b + " = " + calculatorService.addition(a, b));
        System.out.println(a + " - " + b + " = " + calculatorService.subtraction(a, b));
        System.out.println(a + " * " + b + " = " + calculatorService.multiplication(a, b));
        System.out.println(a + " / " + b + " = " + calculatorService.division(a, b));
        System.out.println(a + " squared : " + calculatorService.exponentiation(a));
        System.out.println(b + " squared : " + calculatorService.exponentiation(b));
    }

}
