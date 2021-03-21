package ua.com.alevel.util;

import java.util.List;

public class TriangleAreaUtil {
    public static double CalculateArea(List<Double> a, List<Double> b, List<Double> c) {
        return (Math.abs((a.get(0) - c.get(0)) * (b.get(1) - c.get(1))
                - (a.get(1) - c.get(1)) * (b.get(0) - c.get(0))) ) / 2;
    }
}
