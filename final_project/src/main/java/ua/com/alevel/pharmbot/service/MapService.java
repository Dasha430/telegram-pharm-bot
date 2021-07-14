package ua.com.alevel.pharmbot.service;

import java.util.List;
import java.util.Map;

public interface MapService {
    List<String> calculateNearestDistances(String fromAddress, List<String> tos);

    Map<String, Double> toGeoCoordinates(String address);

    String geocodeToString(Map<String, Double> geocode);
}
