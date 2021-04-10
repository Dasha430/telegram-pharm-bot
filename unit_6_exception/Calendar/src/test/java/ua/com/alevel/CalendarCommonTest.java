package ua.com.alevel;

import org.junit.jupiter.api.Test;
import ua.com.alevel.entity.CalendarDate;
import ua.com.alevel.service.impl.CalendarImpl;

import java.util.ArrayList;
import java.util.List;

public class CalendarCommonTest {

    private CalendarDate cd1 = new CalendarDate();
    private CalendarDate cd2 = new CalendarDate();
    private final CalendarImpl calendar = new CalendarImpl();

    @Test
    public void test(){
        cd1 = calendar.convertToDate("1256 13:20", "m/d/yyyy 00:00");
        cd2 = calendar.convertToDate("/6/", "m/d/yyyy");

        cd1.print();
        calendar.changeFormat(cd1, "mmm-dd-yyyy");
        cd1.print();
        cd2.print();


        List<CalendarDate> datesList= new ArrayList<>();
        datesList.add(cd1);
        datesList.add(cd2);
        datesList = calendar.sortAsc(datesList);
        for (CalendarDate date: datesList) {
            date.print();
        }
        datesList = calendar.sortDesc(datesList);
        for (CalendarDate date: datesList) {
            date.print();
        }
    }
}
