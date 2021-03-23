package ua.com.alevel.service.factory;

import org.reflections.Reflections;
import ua.com.alevel.service.GameOfLifeService;

import java.util.Set;

public class GameOfLifeFactory {
    private static GameOfLifeFactory instance;
    private Reflections reflections;
    Set<Class<? extends GameOfLifeService>> gameServices;

    private GameOfLifeFactory() {
        reflections = new Reflections("ua.com.alevel");
        gameServices = reflections.getSubTypesOf(GameOfLifeService.class);
    }

    public static GameOfLifeFactory getInstance() {
        if (instance == null) {
            instance = new GameOfLifeFactory();
        }
        return instance;
    }

    public GameOfLifeService getGameOfLifeService() {
        for (Class<? extends GameOfLifeService> service: gameServices) {
            if (!service.isAnnotationPresent(Deprecated.class)) {
                try {
                    return service.getDeclaredConstructor().newInstance();
                } catch(Exception e) {
                    System.out.println("Get message");
                }

            }
        }
        throw new RuntimeException();
    }
}
