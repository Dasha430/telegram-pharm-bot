package ua.com.alevel.service.impl;

import org.json.JSONObject;
import ua.com.alevel.service.MapService;
import ua.com.alevel.util.JsonReader;
import ua.com.alevel.util.ParamsEncoder;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;

import static java.lang.Math.*;

public class GoogleMapsService implements MapService {
    private static final String BASEURL = "http://maps.googleapis.com/maps/api/geocode/json";
    private static final double EARTH_RADIUS = 6371.;
    private static final double DEFAULT_DISTANCE_LIMIT = 2.;

    public Map<String, Double> toGeoCoordinates(String address) {
        Map<String, String> params = new HashMap<>();
        Map<String, Double> results = new HashMap<>();

        params.put("address", address);
        String url = BASEURL + '?' + ParamsEncoder.encodeParams(params);
        JSONObject response;
        try {
            response = JsonReader.read(url);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        JSONObject location = response.getJSONArray("results").getJSONObject(0);
        location = location.getJSONObject("geometry");
        location = location.getJSONObject("location");
        double lng = location.getDouble("lng");
        double lat = location.getDouble("lat");

        results.put("lng", lng);
        results.put("lat", lat);
        return results;
    }

    @Override
    public Map<Double, String> calculateDistances(Map<String, Double> from, List<Map<String, Double>> destinations) {

        double fromLng = from.get("lng");
        double fromLat = from.get("lat");
        double distance;
        Map<Double, String> results = new TreeMap<>();

        for (Map<String, Double> dest: destinations) {
            distance = findDistance(fromLng, fromLat, dest);
            if (distance <= DEFAULT_DISTANCE_LIMIT) {
                String coords = dest.get("lng").toString() + "," +dest.get("lat").toString();
                results.put(distance, coords);
            }
        }
        return results;

    }

    private double findDistance(double fromLng, double fromLat, Map<String, Double> dest) {
        Double destLng = dest.get("lng");
        Double destLat = dest.get("lat");
        double dlng = degToRad(fromLng - destLng);
        double dlat = degToRad(fromLat - destLat);
        double a = sin(dlat / 2) * sin(dlat / 2) + cos(degToRad(destLat))
                * cos(degToRad(fromLat)) * sin(dlng / 2) * sin(dlng / 2);
        double c = 2 * atan2(sqrt(a), sqrt(1 - a));
        return c * EARTH_RADIUS;
    }

    private double degToRad(final double degree) {
        return degree * (Math.PI / 180);
    }
}
