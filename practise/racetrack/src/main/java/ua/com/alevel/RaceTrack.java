package ua.com.alevel;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class RaceTrack {

    private final Set<Horse> horses;

    private final Map<String, Integer> finished;

    private final int distance;

    private final AtomicInteger place;

    private CountDownLatch startCounter;

    private CountDownLatch finishCounter;

    public RaceTrack(int distance) {
        this.distance = distance;
        horses =  ConcurrentHashMap.newKeySet();
        finished = new ConcurrentHashMap<>();
        place = new AtomicInteger();
    }

    public void addHorse(Horse horse) {
        this.horses.add(horse);
        horse.setRaceTrack(this);
    }

    public int getDistance() {
        return distance;
    }

    public AtomicInteger getPlace() {
        return place;
    }

    public CountDownLatch getStartCounter() {
        return startCounter;
    }

    public CountDownLatch getFinishCounter() {
        return finishCounter;
    }

    public void addToFinished(String name, int place) {
        this.finished.put(name, place);
    }

    public Map<String, Integer> getFinished() {
        return finished;
    }

    public synchronized void readySetGo() {
        place.set(1);
        startCounter = new CountDownLatch(horses.size());
        finishCounter = new CountDownLatch(horses.size());
        System.out.println("Race started");

        try {

            for (Horse h: horses) {
                new Thread(h, h.getName()).start();
            }
            startCounter.await();
            finishCounter.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Race finished");

    }
}
