package ua.com.alevel.service.factory;

import org.reflections.Reflections;
import ua.com.alevel.service.ConsoleHelperService;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class ConsoleFactory {

    private static ConsoleFactory instance;
    private Reflections reflections;
    private Set<Class<? extends ConsoleHelperService>> chServices;

    private ConsoleFactory() {
        reflections = new Reflections("ua.com.alevel");
        chServices = reflections.getSubTypesOf(ConsoleHelperService.class);
    }

    public static ConsoleFactory getInstance() {
        if (instance == null) {
            instance = new ConsoleFactory();
        }
        return instance;
    }

    public ConsoleHelperService getConsoleHelperService() {

        for (Class<? extends ConsoleHelperService> service: chServices) {
            if (!service.isAnnotationPresent(Deprecated.class)) {
                try {
                    return service.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }

        }
        throw new RuntimeException();

    }
}

