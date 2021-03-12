package ua.com.alevel;

import java.util.Scanner;

public class App {
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input value (string that contains numbers): ");
        String str = sc.nextLine();

        System.out.println("Output value: " + calculateSumFromStr(str));

    }

    public static int calculateSumFromStr(String str) {
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) sum += Character.getNumericValue(str.charAt(i));
        }
        return sum;
    }
}
