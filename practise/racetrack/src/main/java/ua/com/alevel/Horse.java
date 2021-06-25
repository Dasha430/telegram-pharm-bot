package ua.com.alevel;


public class Horse implements Runnable{


    private final String name;

    private RaceTrack raceTrack;

    private int runDistance = 0;

    private static final int MAX_SLEEP = 500;

    private static final int MIN_SLEEP = 400;

    private static final int MAX_RUN = 200;

    private static final int MIN_RUN = 100;

    public Horse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRaceTrack(RaceTrack raceTrack) {
        this.raceTrack = raceTrack;
    }

    @Override
    public void run() {

        int place;
        System.out.println(this.name + " started running");
        while (runDistance < raceTrack.getDistance()) {
            if (runDistance == 0) {
                raceTrack.getStartCounter().countDown();
            }
            try {
                this.runDistance += (int) (Math.random() * (MAX_RUN - MIN_RUN)) + MIN_RUN;
                Thread.sleep((int) (Math.random() * (MAX_SLEEP - MIN_SLEEP)) + MIN_SLEEP);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        place = raceTrack.getPlace().getAndIncrement();
        raceTrack.addToFinished(this.name, place);
        raceTrack.getFinishCounter().countDown();

        System.out.println(this.name + " finished, place #" + place);

    }
}
