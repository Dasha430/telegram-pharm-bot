package ua.com.alevel;

import ua.com.alevel.view.MathSetView;

public class MathSetMain {

    public static void main(String[] args) {

        MathSetView msv = new MathSetView();

        try {
            msv.run();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
