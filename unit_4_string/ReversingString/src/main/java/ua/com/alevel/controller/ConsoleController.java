package ua.com.alevel.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ua.com.alevel.service.ReverseStringService;
import ua.com.alevel.service.impl.ReverseStringImpl;

public class ConsoleController {
    private final ReverseStringService rss = new ReverseStringImpl();


    public void run(String src) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while(true) {

                try {
                    String option = bufferedReader.readLine();

                    switch(option) {
                        case "0":
                            System.exit(0);
                        case "1":
                            System.out.println(rss.reverse(src));
                            break;
                        case "2":
                            System.out.println("Enter a substring: ");
                            String substring = bufferedReader.readLine();
                            System.out.println(rss.reverse(src, substring));
                            break;
                        case "3":
                            System.out.println("Enter the first index: ");
                            int firstIndex = Integer.parseInt(bufferedReader.readLine());
                            System.out.println("Enter the last index: ");
                            int lastIndex = Integer.parseInt(bufferedReader.readLine());
                            System.out.println(rss.reverse(src, firstIndex, lastIndex));
                            break;
                        default:
                            throw new RuntimeException("Wrong index");
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            System.out.println("Repeat logic or press 0 to exit");

        }
    }
}
