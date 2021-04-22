package ua.com.alevel.view;

import lombok.SneakyThrows;
import ua.com.alevel.controller.MathSetController;
import ua.com.alevel.service.impl.MathSet;

import java.io.BufferedReader;

import java.io.InputStreamReader;

public class MathSetView {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    MathSetController msc = new MathSetController();

    @SneakyThrows
    public void run () {

        System.out.println("Hello! This is a demonstration of the MathSet library.");
        System.out.println("Double type will be used as an example.");




        System.out.println("Enter number of elements:");
        int num = Integer.parseInt(reader.readLine());
        Double[] doubleArr = new Double[num];
        for (int i = 0; i < num; i++) {
            System.out.print("Enter element # " + (i + 1) + ": ");
            doubleArr[i] = Double.parseDouble(reader.readLine());
            System.out.println();
        }
        MathSet<Double> msDouble = new MathSet<>(doubleArr);
        System.out.println("Select action:");
        System.out.println("1 - add element");
        System.out.println("2 - join with another MathSet");
        System.out.println("3 - sort in ascending order");
        System.out.println("4 - sort in descending order");
        System.out.println("5 - get element by index");
        System.out.println("6 - get max element");
        System.out.println("7 - get min element");
        System.out.println("8 - get average");
        System.out.println("9 - get median");
        System.out.println("10 - squash");
        System.out.println("11 - clear set");
        System.out.println("12 - delete elements");
        System.out.println("0 - exit");
        msc.run(msDouble, reader.readLine());





    }
}
