package ua.com.alevel.util;


import java.util.ArrayList;
import java.util.List;


public class UniqueNameFinderUtil {

    public static String findFirstUnique(List<String> names) {
        ArrayList<String> unique = new ArrayList();
        for (String name: names) {
            if (unique.contains(name)) {
                unique.remove(name);
            } else {
                unique.add(name);
            }
        }
        return unique.get(0);
    }
}
