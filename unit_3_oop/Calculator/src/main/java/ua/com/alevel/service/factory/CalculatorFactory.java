package ua.com.alevel.service.factory;


import org.reflections.Reflections;
import ua.com.alevel.service.CalculatorService;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class CalculatorFactory {
    private static CalculatorFactory instance;
    private Reflections reflections;
    Set<Class<? extends CalculatorService>> calcServices;

    private CalculatorFactory() {
        reflections = new Reflections("ua.com.alevel");
        calcServices = reflections.getSubTypesOf(CalculatorService.class);
    }

    public static CalculatorFactory getInstance() {
        if (instance == null) {
            instance = new CalculatorFactory();
        }
        return instance;
    }

    public CalculatorService getCalculatorService() {

        for (Class<? extends CalculatorService> service : calcServices) {
            if(!service.isAnnotationPresent(Deprecated.class)) {
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
