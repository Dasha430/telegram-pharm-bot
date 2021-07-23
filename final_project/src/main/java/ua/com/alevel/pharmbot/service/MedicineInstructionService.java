package ua.com.alevel.pharmbot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public String findInstruction(String name, String formName) {
        log.info("Start finding instructions");
        Medicine medicine = repo.findFirstByNameAndFormName(name, formName).orElse(null);
        log.info("Finish finding instructions");

        if (medicine == null) {
            log.info("Instructions not found");
        }
        return medicine.getInstruction();
    }

    @Transactional
    public Set<String> findAllExistingForms(String name) {

        log.info("Start finding forms of " + name);
        List<Medicine> results = repo.findByName(name);
        Set<String> forms = new HashSet<>();
        for (Medicine m: results) {
            forms.add( repo.findFormById(m.getId()));
        }
        log.info("Found " + forms.size() + " results");
        return forms;
    }
}
