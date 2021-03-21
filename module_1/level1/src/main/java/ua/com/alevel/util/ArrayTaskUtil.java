package ua.com.alevel.util;

import java.util.ArrayList;
import java.util.List;

public class ArrayTaskUtil {
    public static int uniqueNumbersCount (List<? extends Number> array) {
        int uniqueCount = 0;
        List < Number> uniqueNumbers = new ArrayList<>();
        for (Number n:array) {
            if (!uniqueNumbers.contains(n)) {
                uniqueCount++;
                uniqueNumbers.add(n);
            }
        }
        return uniqueCount;
    }
    public static boolean isUnique(Number num, List<? extends Number> array) {

        int count = 0;
        for (Number n:array) {
            if (num.equals(n)) {
                count++;
            }
        }
        return count == 1 ;
    }
}
