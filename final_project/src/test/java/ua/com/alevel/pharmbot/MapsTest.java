package ua.com.alevel.pharmbot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.pharmbot.service.impl.GoogleMapsService;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MapsTest {

    @Autowired
    private GoogleMapsService service;

    @Test
    void toGeoCoordinatesTest(){
        String address = "Украина, Харьков, проспект Жуковского, 11";
        Map<String, Double> res = service.toGeoCoordinates(address);

    }

}
