package ua.com.alevel;

import ua.com.alevel.service.OrderedList;
import ua.com.alevel.view.OrderedListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class OrderedListMain {


    public static void main(String[] args) {
        OrderedListView view = new OrderedListView();
        try {
            view.run();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
