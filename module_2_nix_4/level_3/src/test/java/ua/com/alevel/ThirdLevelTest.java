package ua.com.alevel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import ua.com.alevel.view.FindShortestPathView;

import java.io.File;
import java.io.IOException;

public class ThirdLevelTest {

    @Test
    public void test(){
        FindShortestPathView view = new FindShortestPathView();
        view.run();
    }
}
