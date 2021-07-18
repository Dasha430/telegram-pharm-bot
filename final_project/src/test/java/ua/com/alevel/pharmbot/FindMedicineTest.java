package ua.com.alevel.pharmbot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.pharmbot.model.FormName;
import ua.com.alevel.pharmbot.model.Medicine;
import ua.com.alevel.pharmbot.model.records.MedInPharmacyRecord;
import ua.com.alevel.pharmbot.service.FindMedicineService;
import ua.com.alevel.pharmbot.service.MedicineInstructionService;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FindMedicineTest {

    @Autowired
    private FindMedicineService service;

    @Autowired
    private MedicineInstructionService instructionService;

    @Test
    void getNearestAddressesWhereMedicinePresentTest(){
        String name = "Но-шпа";
        String address = "lng:36.2931901 lat:50.053909";
        List<String> results =  service.getNearestAddressesWhereMedicinePresent(name, address);
        List<MedInPharmacyRecord> records = service.getByNameAndAddress(name, address);
    }

    @Test
    void findAllExistingFormsTest(){
        String name = "Но-шпа";
        Set<String> results = instructionService.findAllExistingForms(name);
        assertThat(results).isNotEmpty();
        assertThat(results).isNotNull();
    }

    @Test
    void findInstructionTest() {
        String name = "Но-шпа";
        String formName = FormName.TABLET.name();
        String medicineInstr = instructionService.findInstruction(name, formName);
        assertThat(medicineInstr).isNotNull();
    }
}
