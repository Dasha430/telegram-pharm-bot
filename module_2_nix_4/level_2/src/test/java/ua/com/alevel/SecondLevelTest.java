package ua.com.alevel;

import org.junit.Test;
import ua.com.alevel.view.UniqueNameFinderView;

import java.io.IOException;

public class SecondLevelTest {

    @Test
    public void test() {
        UniqueNameFinderView view = new UniqueNameFinderView();

        try {
            view.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
