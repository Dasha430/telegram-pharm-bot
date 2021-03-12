package ua.com.alevel;

import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;

public class App 
{
    public static void main( String[] args ) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input data (string that contains latin/cyrillic symbols): ");
        String str = sc.nextLine();
        System.out.println();
        Map<Character, Integer> lettersCount = new TreeMap<>();
        calculateLettersInStr(str, lettersCount);
        int i = 1;
        System.out.println("Output data:");
        for (Map.Entry entry : lettersCount.entrySet()) {
            System.out.println(i + ". " + entry.getKey() + " - " + entry.getValue());

        }
    }
    public static void calculateLettersInStr(String str, Map<Character, Integer> lettersCount)  {
        int sum = 0;

        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i)) && !lettersCount.containsKey(str.charAt(i))) {
                int count = 0;
                for (int j = i; j < str.length(); j++) {
                    if (str.charAt(j) == str.charAt(i)) count++;
                }
                lettersCount.putIfAbsent(str.charAt(i), count);
            }
        }

    }
}
