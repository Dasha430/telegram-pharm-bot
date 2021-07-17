package ua.com.alevel.pharmbot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.pharmbot.model.records.MedInPharmacyRecord;
import ua.com.alevel.pharmbot.service.FindMedicineService;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FindMedicineTest {

    @Autowired
    private FindMedicineService service;

    @Test
    void getNearestAddressesWhereMedicinePresentTest(){
        String name = "Но-шпа";
        String address = "lng:36.2931901 lat:50.053909";
        List<String> results =  service.getNearestAddressesWhereMedicinePresent(name, address);
        List<MedInPharmacyRecord> records = service.getByNameAndAddress(name, address);
    }
}
