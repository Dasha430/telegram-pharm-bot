package ua.com.alevel.view;


import java.io.IOException;

public class AllModuleTasksView {

    public void run() throws IOException {

        DateFormatChangeView dateFormatChangeView = new DateFormatChangeView();
        UniqueNameFinderView uniqueNameFinderView = new UniqueNameFinderView();
        FindShortestPathView findShortestPathView = new FindShortestPathView();


        System.out.println("These are tasks of module 2");
        System.out.println("Task #1");
        System.out.println();
        dateFormatChangeView.run();
        System.out.println();

        System.out.println("Task #2");
        System.out.println();
        uniqueNameFinderView.run();
        System.out.println();

        System.out.println("Task #3");
        System.out.println();
        findShortestPathView.run();
        System.out.println();


    }
}
