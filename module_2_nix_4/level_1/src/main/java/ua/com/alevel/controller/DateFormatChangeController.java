package ua.com.alevel.controller;

import ua.com.alevel.service.DateFormatChangeUtil;

import java.util.ArrayList;
import java.util.List;

public class DateFormatChangeController {

    public void run(List<String> originalDates) {
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < originalDates.size(); i++) {
            resultList.add(DateFormatChangeUtil.changeFormat(originalDates.get(i)));
        }
        for (String date: resultList) {
            System.out.println(date);
        }

    }
}
