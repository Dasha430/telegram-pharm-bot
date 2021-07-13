package ua.com.alevel.service;

public class DateFormatChangeUtil {

    public static String changeFormat(String originalDate) {
        String[] date = null;
        if (originalDate.contains("/")) {
            date = originalDate.split("/");

        } else if (originalDate.contains("-")) {
            date = originalDate.split("-");
        } else {
            return originalDate;
        }
        if (date.length != 3) {
            return originalDate;
        }
        for (int i = 0; i < date.length - 1; i++) {
            if (date[i].length() < 2) {
                return originalDate;
            }
            if (date[i].charAt(0) == '0') {
                if (date[i].length() != 2) {
                    return originalDate;
                }
                if (Integer.parseInt(String.valueOf(date[i].charAt(1))) < 1 ||
                        Integer.parseInt(String.valueOf(date[i].charAt(1))) > 31) {
                    return originalDate;
                }
            } else {
                if (Integer.parseInt(date[i]) < 1 ) {
                    return originalDate;
                }

            }
        }
        return originalDate.replaceAll("[/-]", "");
    }
}
