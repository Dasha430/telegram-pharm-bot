package ua.com.alevel;

import ua.com.alevel.entity.User;
import ua.com.alevel.mapper.CsvMapper;
import ua.com.alevel.parser.Parser;
import ua.com.alevel.structure.ParsedData;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CSVParserMain {


    public static void main(String[] args) {
        final String FILE = "unit_12_csv_parser/CSVParser/src/main/resources/users.csv";

        Parser parser = new Parser();
        CsvMapper mapper = new CsvMapper();
        ParsedData usersData = parser.parse(FILE);

        try {
            List<User> users = mapper.map(usersData, User.class);

            System.out.println("Results: ");
            for (User user: users) {
                System.out.println("id: " + user.getId());
                System.out.println("name: " + user.getName());
                System.out.println("age: " + user.getAge());
                System.out.println("role: " + user.getRole());
                System.out.println();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }



    }
}
