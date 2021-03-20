package ua.com.alevel.util;

public class ReversionUtil {

    public static String reversion(String src) {
        String[] words = src.split(" ");
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < words.length; ++i) {
            char[] sourceWord = words[i].toCharArray();

            for(int j = sourceWord.length - 1; j >= 0; --j) {
                sb.append(sourceWord[j]);
            }

            if (i != words.length - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }
}