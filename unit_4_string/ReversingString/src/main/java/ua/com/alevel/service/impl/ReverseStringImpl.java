package ua.com.alevel.service.impl;

import ua.com.alevel.service.ReverseStringService;
import ua.com.alevel.util.ReversionUtil;

public class ReverseStringImpl implements ReverseStringService {


    public String reverse(String src) {

        return ReversionUtil.reversion(src);
    }

    public String reverse(String src, String dest) {
        StringBuilder sb = new StringBuilder(src);

        try {
            int start = sb.indexOf(dest);
            int end = start + dest.length();
            if (!src.contains(dest)) {
                throw new RuntimeException("There is no such substring in your string");
            } else {
                sb = sb.replace(start, end, this.reverse(sb.substring(start, end)));
                return sb.toString();
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String reverse(String src, int firstIndex, int lastIndex) {
        StringBuilder sb = new StringBuilder(src);

        try {
            if (firstIndex <= src.length() && lastIndex <= src.length()) {
                if (firstIndex > lastIndex) {
                    throw new RuntimeException("The first index must be smaller than the last");
                } else {
                    return sb.replace(firstIndex, lastIndex + 1, this.reverse(sb.substring(firstIndex, lastIndex + 1))).toString();
                }
            } else {
                throw new RuntimeException("The indexes are bigger than your string");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}