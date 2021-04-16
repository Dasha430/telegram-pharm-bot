package ua.com.alevel.view;

import ua.com.alevel.User;
import ua.com.alevel.controller.OrderedListController;
import ua.com.alevel.service.OrderedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class OrderedListView {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    OrderedListController controller = new OrderedListController();

    public void run() throws IOException {
        System.out.println("Hello! This is a demonstration of the OrderedList library");
        System.out.println("Class User will be used as an example.");
        System.out.println("Please enter how many users you would like to put in the list:");
        int users = Integer.parseInt(reader.readLine());

        OrderedList<User> userList = new OrderedList<>();

        for (int i = 0; i < users; i++) {
            System.out.println("Enter user's name:");
            String name = reader.readLine();
            System.out.println("Enter user's age:");
            int age = Integer.parseInt(reader.readLine());
            User u = new User(name, age);
            userList.add(u);

        }

        controller.run(userList);

    }
}
