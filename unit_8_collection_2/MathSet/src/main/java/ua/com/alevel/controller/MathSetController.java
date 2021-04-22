package ua.com.alevel.controller;

import ua.com.alevel.service.AbstractMathSet;
import ua.com.alevel.service.impl.MathSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MathSetController {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void run (MathSet ms, String option) throws IOException {


        while(true) {

            switch (option) {
                case "0":
                    System.exit(0);
                case "1":
                    System.out.println("Enter element:");
                    String elem = reader.readLine();

                    ms.add(Double.parseDouble(elem));

                    System.out.println("Element was added!");
                    printSet(ms);
                    break;
                case "2":
                    System.out.println("Enter number of elements in another set");
                    int number = Integer.parseInt(reader.readLine());

                    Double[] doubleArr = new Double[number];
                    for (int i = 0; i < number; i++) {
                        System.out.print("Enter element # " + (i + 1) + ": ");
                        doubleArr[i] = Double.parseDouble(reader.readLine());
                        System.out.println();
                    }
                    MathSet<Double> msDouble = new MathSet<>(doubleArr);
                    ms.join(msDouble);


                    System.out.println("Sets were joined!");
                    printSet(ms);
                    break;
                case "3" :
                    System.out.println("Set before sorting:");
                    printSet(ms);
                    ms.sortAsc();
                    System.out.println("After sorting:");
                    printSet(ms);
                    break;
                case "4":
                    System.out.println("Set before sorting:");
                    printSet(ms);
                    ms.sortDesc();
                    System.out.println("After sorting:");
                    printSet(ms);
                    break;
                case "5":
                    System.out.println("Enter index of the element you want to get");
                    int index = Integer.parseInt(reader.readLine());

                    System.out.println("The element is " + ms.get(index));
                    break;

                case "6":
                    System.out.println("The biggest element of the set is: " + ms.getMax());
                    break;
                case "7":
                    System.out.println("The smallest element of the set is: " + ms.getMin());
                    break;
                case "8":
                    System.out.println("Average of the set elements is: " + ms.getAverage());
                    break;
                case "9":
                    System.out.println("Median of the set is: " + ms.getMedian());
                    break;
                case "10":
                    System.out.println("Enter first index:");
                    int first = Integer.parseInt(reader.readLine());
                    System.out.println("Enter last index:");
                    int last = Integer.parseInt(reader.readLine());
                    AbstractMathSet<Double> doubleSet = ms.squash(first, last);
                    System.out.println("Set after squashing:");
                    printSet(doubleSet);

                    break;
                case "11":
                    ms.clear();
                    System.out.println("Set is cleared!");
                    break;
                case "12":
                    System.out.println("How many elements you want to delete?");
                    int n = Integer.parseInt(reader.readLine());
                    Object[] elements = new Object[n];

                    for (int i = 0; i < n; i++) {
                        System.out.print("Enter element # " + (i + 1) + ": ");
                        elements[i] = Double.parseDouble(reader.readLine());
                    }

                    ms.clear(elements);
                    System.out.println("Elements were deleted!");
                    printSet(ms);
                    break;
                default :
                    System.out.println("Wrong option. Try again");

            }
            System.out.println("Enter 0 to exit or choose another action");
            option = reader.readLine();
        }
    }

    public void printSet(AbstractMathSet ms) {
        System.out.print("[ ");
        for (Object obj: ms.toArray()) {
            if (obj != null) {
                System.out.print(obj + "   ");
            }
        }
        System.out.print("]");
        System.out.println();
    }


}
