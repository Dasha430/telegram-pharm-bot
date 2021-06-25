package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;

public class RaceMain {

    private static final int DISTANCE = 1000;

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        RaceTrack track = new RaceTrack(DISTANCE);
        track.addHorse(new Horse("Horse 1"));
        track.addHorse(new Horse("Horse 2"));
        track.addHorse(new Horse("Horse 3"));
        track.addHorse(new Horse("Horse 4"));
        track.addHorse(new Horse("Horse 5"));

        boolean rightInput = true;
        String bet = "";
        do {
            System.out.println("Choose a horse you want to bet on");
            System.out.println("1 - Horse 1");
            System.out.println("2 - Horse 2");
            System.out.println("3 - Horse 3");
            System.out.println("4 - Horse 4");
            System.out.println("5 - Horse 5");

            String option;
            try {
                option = reader.readLine();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }


            switch (option) {
                case "1":
                    bet = "Horse 1";
                    break;
                case "2":
                    bet = "Horse 2";
                    break;
                case "3":
                    bet = "Horse 3";
                    break;
                case "4":
                    bet = "Horse 4";
                    break;
                case "5":
                    bet = "Horse 5";
                    break;
                default:
                    System.out.println("Wrong input! Try again");
                    rightInput = false;
            }
        } while (!rightInput);

        track.readySetGo();

        System.out.println("The horse you had bet on is on " + track.getFinished().get(bet) + " place");
    }
}
