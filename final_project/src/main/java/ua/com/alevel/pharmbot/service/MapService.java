package ua.com.alevel.service;

import java.util.List;
import java.util.Map;

public interface MapService {
    Map<Double, String> calculateDistances(Map<String, Double> from, List<Map<String, Double>> destinations);
}
