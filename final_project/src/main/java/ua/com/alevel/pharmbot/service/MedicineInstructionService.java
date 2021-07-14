package ua.com.alevel.pharmbot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.alevel.pharmbot.model.FormName;
import ua.com.alevel.pharmbot.model.Medicine;
import ua.com.alevel.pharmbot.repository.MedicineRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class MedicineInstructionService {

    MedicineRepository repo;

    public MedicineInstructionService(MedicineRepository repo) {
        this.repo = repo;
    }

    public String findInstruction(String name, FormName formName) {
        String instruction;
        log.info("Start finding instructions");
        Medicine medicine = repo.findFirstByNameAndForm_Name(name, formName).orElse(null);
        log.info("Finish finding instructions");

        if (medicine == null) {
            log.info("Instructions not found");
        }
        return medicine.getInstruction();
    }

    public String findInstruction(String name) {
        String instruction;
        log.info("Start finding instructions");
        List<Medicine> medicines = repo.findByName(name);
        log.info("Finish finding instructions");

        if (medicines == null) {
            log.info("Instructions not found");
            return null;
        }
        return medicines.get(0).getInstruction();
    }

    public Set<FormName> findAllExistingForms(String name) {

        log.info("Start finding forms of " + name);
        List<Medicine> results = repo.findByName(name);
        Set<FormName> forms = new HashSet<>();
        for (Medicine m: results) {
            forms.add(m.getForm().getName());
        }
        log.info("Found " + forms.size() + " results");
        return forms;
    }
}
