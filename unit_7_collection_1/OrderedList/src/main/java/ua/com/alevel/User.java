package ua.com.alevel;

import lombok.Getter;

@Getter
public class User implements Comparable<User> {

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
