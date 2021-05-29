package ua.com.alevel.mapper;

import ua.com.alevel.annotation.CsvCell;
import ua.com.alevel.structure.ParsedData;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvMapper {

    public <T> List<T> map(ParsedData parsedData, Class<T> c) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {

        List <T> input = new ArrayList<>();

        Constructor<T> constructor = c.getConstructor();

        List<Field> fields = Arrays.stream(c.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(CsvCell.class))
                .filter(Field::trySetAccessible)
                .collect(Collectors.toList());
        for (int i = 0; i < parsedData.rowCount(); i++) {

            T instance = constructor.newInstance();

            for (Field field: fields) {

                CsvCell cell = field.getAnnotation(CsvCell.class);
                String header = cell.value();
                String data = parsedData.get(i, header);
                if (field.getType() == int.class || field.getType() == Integer.class) {
                    field.set(instance, Integer.parseInt(data));
                } else if (field.getType() == double.class || field.getType() == Double.class) {
                    field.set(instance, Double.parseDouble(data));
                } else if (field.getType() == float.class || field.getType() == Float.class) {
                    field.set(instance, Float.parseFloat(data));
                } else if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                    field.set(instance, Boolean.parseBoolean(data));
                } else if (field.getType().isEnum()) {
                    field.set(instance, Enum.valueOf((Class<Enum>) field.getType(), data.toUpperCase()));
                } else {
                    field.set(instance, data);
                }
            }

            input.add(instance);

        }

        return input;
    }
}
