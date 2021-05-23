package ua.com.alevel.annotation.analyzer;

import ua.com.alevel.annotation.PropertyKey;
import ua.com.alevel.entity.AppProperties;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class PropertyKeyAnalyzer {


    public void analyze(Object instance) throws IllegalAccessException {
        Properties properties = loadProperties();

        Class<?> classOfInstance = instance.getClass();
        for (Field field: classOfInstance.getDeclaredFields()) {
            if (field.isAnnotationPresent(PropertyKey.class)) {
                PropertyKey key = field.getAnnotation(PropertyKey.class);
                if (field.getType().getName().equals("int") ) {
                    field.set(instance,Integer.parseInt(properties.getProperty(key.value())));
                }
                else {
                    field.set(instance,properties.getProperty(key.value()));
                }
            }
        }

    }

    public Properties loadProperties() {
        Properties properties = new Properties();

        try(InputStream input = AppProperties.class.getResourceAsStream("/app.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return properties;
    }


}
