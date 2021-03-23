package ua.com.alevel.util;

import java.util.ArrayList;
import java.util.List;

public class ParenthesesTaskUtil {
    public static boolean isAcceptable(String src) {
        if (src.isEmpty()) {
            return true;
        }

        List<Character> parentheses = new ArrayList<>();
        for(int i = 0;i < src.length(); i++) {
            char currentChar = src.charAt(i);
            if (currentChar == '(' || currentChar == '[' || currentChar == '{') {
                parentheses.add(currentChar);
            }
            int lastIndex = 0;
            if (parentheses.size() != 0) {
                lastIndex = parentheses.size() - 1;
            }
            if (!parentheses.isEmpty() && isEndingBracket(parentheses.get(lastIndex), currentChar)) {
                parentheses.remove(parentheses.size() - 1);
            }

        }
        return parentheses.isEmpty();
    }
    public static boolean isEndingBracket(char open, char end) {

        switch(open) {
            case '(':
                return end == ')';
            case '[':
                return end == ']';
            case '{':
                return end == '}';
        }
        return false;
    }
}
