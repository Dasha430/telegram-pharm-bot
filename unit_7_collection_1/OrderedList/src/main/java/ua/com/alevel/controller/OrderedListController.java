package ua.com.alevel.controller;

import ua.com.alevel.User;
import ua.com.alevel.service.OrderedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class OrderedListController {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void run (OrderedList<User> userList) throws IOException {

        System.out.println("Select what you would like to do with the list");
        System.out.println("1 - check size");
        System.out.println("2 - check if list is empty");
        System.out.println("3 - remove user by index");
        System.out.println("4 - add user");
        System.out.println("5 - see the list's elements");
        System.out.println("6 - get user by index");
        System.out.println("7 - create sublist from current list");
        System.out.println("8 - remove all the elements from the list");
        System.out.println("0 - exit");

        String option = reader.readLine();
        while (true) {
            switch (option) {
                case "0":
                    System.exit(0);
                case "1":
                    System.out.println("The size is : " + userList.size());
                    break;
                case "2":
                    System.out.println("Result of isEmpty(): " + userList.isEmpty());
                    break;

                case "3":
                    System.out.println("Enter user's index in list:");

                    userList.remove(Integer.parseInt(reader.readLine()));
                    System.out.println("The user is removed!");
                    printList(userList);
                    break;
                case "4":
                    User userToAdd = createUser();
                    userList.add(userToAdd);
                    System.out.println("The user is added!");
                    printList(userList);
                    break;
                case "5":
                    System.out.println("The list contains elements: ");
                    printList(userList);
                    break;
                case "6":
                    System.out.println("Enter user's index in list:");
                    System.out.println(userList.get(Integer.parseInt(reader.readLine())));
                    break;
                case "7":
                    System.out.println("To create a sublist from the current list enter:");
                    System.out.println("First index:");
                    int first = Integer.parseInt(reader.readLine());
                    System.out.println("Second index:");
                    int second = Integer.parseInt(reader.readLine());
                    List<User> list = userList.subList(first, second);
                    System.out.println("The sublist is:");
                    printList(list);
                    break;

                case "8":
                    userList.removeAll(userList);
                    System.out.println("All elements removed. The size is now " + userList.size());
                    break;
                default:
                    throw new RuntimeException("wrong option");

            }
            System.out.println("Enter 0 to exit or repeat the logic");
            option = reader.readLine();
        }



    }

    private void printList(List<User> list) {
        for (User u: list) {
            System.out.println(u);
        }
    }

    private User createUser() throws IOException {
        System.out.println("Enter user's name:");
        String name = reader.readLine();
        System.out.println("Enter user's age:");
        int age = Integer.parseInt(reader.readLine());
        return new User(name, age);
    }
}
