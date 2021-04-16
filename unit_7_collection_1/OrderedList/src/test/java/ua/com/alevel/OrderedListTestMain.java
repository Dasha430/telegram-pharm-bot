package ua.com.alevel;

import lombok.Getter;
import org.junit.jupiter.api.Test;
import ua.com.alevel.service.OrderedList;

import java.util.List;

public class OrderedListTestMain {

    @Test
    public static void main(String[] args) {
        List<Integer> orderedList = new OrderedList<>();
        orderedList.add(-4);
        orderedList.add(-7);
        orderedList.add(0);
        orderedList.add(5);
        orderedList.add(3);

        orderedList.add(-4);
        orderedList.add(8);
        orderedList.add(0);
        orderedList.add(12);
        orderedList.add(3);

        List<Integer> orderedList2 = new OrderedList<>();
        orderedList2.addAll(orderedList);


        System.out.println("Ordered list");
        for (int i: orderedList) {
            System.out.println(i);
        }

        System.out.println("Ordered list2");
        for (int i: orderedList2) {
            System.out.println(i);
        }

        orderedList2.remove((Integer) 3);
        orderedList2.remove(4);

        System.out.println("Ordered list2");
        for (int i: orderedList2) {
            System.out.println(i);
        }

        System.out.println("last element: " + orderedList2.get(orderedList2.size() - 1));
        Object[] arr = orderedList.toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        System.out.println(orderedList.indexOf(-4));
        System.out.println(orderedList.lastIndexOf(-4));

        orderedList2.remove((Integer)12);
        orderedList.removeAll(orderedList2);
        System.out.println("Ordered list");
        for (int i: orderedList) {
            System.out.println(i);
        }

        List<User> userList = new OrderedList<>();
        User u1 = new User("aaaa", 12);
        userList.add(u1);
        User u2 = new User("tttt", 25);
        userList.add(u2);
        User u3 = new User("dddd", 15);
        userList.add(u3);
        User u4 = new User("ffff", 50);
        userList.add(u4);

        for (User u: userList) {
            System.out.println(u);
        }

        List<User> userList2 = new OrderedList<>();
        userList2.addAll(1, userList);

        for (User u: userList2) {
            System.out.println(u);
        }

        userList2.remove(u3);
        userList2.remove(u3);

        for (User u: userList2) {
            System.out.println(u);
        }

        System.out.println();
        List<User> list3 = userList.subList(1, 3);
        for (User u: list3) {
            System.out.println(u);
        }

        System.out.println(userList.containsAll(list3));

    }

    @Getter
    private static class User implements Comparable<User> {
        String name;
        int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(User o) {
            return this.name.compareTo(o.getName());
        }

        @Override
        public String toString() {
            return "{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
