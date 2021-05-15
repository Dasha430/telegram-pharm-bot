package ua.com.alevel;

import org.junit.Test;
import ua.com.alevel.view.DateFormatChangeView;

import java.io.IOException;

public class FirstLevelTest {

    @Test
    public void test() {
        DateFormatChangeView view = new DateFormatChangeView();
        try {
            view.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
