package ua.com.alevel.pharmbot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import ua.com.alevel.pharmbot.service.MapService;
import ua.com.alevel.util.JsonReader;
import ua.com.alevel.util.ParamsEncoder;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;

import static java.lang.Math.*;

@Service
@Slf4j
public class GoogleMapsService implements MapService {
    private static final String BASEURL = "http://maps.googleapis.com/maps/api/geocode/json";
    private static final double EARTH_RADIUS = 6371.;
    private static final double DEFAULT_DISTANCE_LIMIT = 2.;

    @Override
    public Map<String, Double> toGeoCoordinates(String address) {
        Map<String, String> params = new HashMap<>();
        Map<String, Double> results = new HashMap<>();

        log.info("Geocoding address " + address);
        params.put("address", address);
        params.put("sensor", "false");
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

        log.info("Geocoding finished successfully with lng: " + lng + " and lat: " +  lat);
        return results;
    }

    private String toStrAddress(String latlng) {
        Map<String, String> params = new HashMap<>();
        params.put("language", "uk");
        params.put("sensor", "false");
        params.put("latlng", latlng);
        String url = BASEURL + '?' + ParamsEncoder.encodeParams(params);

        try {
            JSONObject response = JsonReader.read(url);
            JSONObject location = response.getJSONArray("results").getJSONObject(0);
            return location.getString("formatted_address");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public List<String> calculateNearestDistances(String fromAddress, List<String> tos) {

        Map<String, Double> from = geocodeToMap(fromAddress);
        List<Map<String, Double>> destinations = new ArrayList<>();

        for (String to: tos) {
            destinations.add(geocodeToMap(to));
        }

        double fromLng = from.get("lng");
        double fromLat = from.get("lat");
        double distance;
        List<String> results = new ArrayList<>();

        log.info("Start calculating distances");
        for (Map<String, Double> dest: destinations) {
            distance = findDistance(fromLng, fromLat, dest);
            if (distance <= DEFAULT_DISTANCE_LIMIT) {
                String coords = dest.get("lat").toString() + "," +dest.get("lng").toString();
                results.add(toStrAddress(coords));
            }
        }
        log.info("Finish calculating distances");
        return results;

    }

    private Map<String, Double> geocodeToMap(String geocode) {
        Map<String, Double> results = new HashMap<>();

        String[] coords = geocode.split(" ");
        results.put(coords[0].split(":")[0], Double.parseDouble(coords[0].split(":")[1]));
        results.put(coords[1].split(":")[0], Double.parseDouble(coords[1].split(":")[1]));
        return results;
    }

    @Override
    public String geocodeToString(Map<String, Double> geocode) {
        return "lng:" + geocode.get("lng") + " lat:" + geocode.get("lat");
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
