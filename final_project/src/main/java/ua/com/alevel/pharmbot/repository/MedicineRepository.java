package ua.com.alevel.pharmbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.alevel.pharmbot.model.FormName;
import ua.com.alevel.pharmbot.model.Medicine;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MedicineRepository extends JpaRepository<Medicine, UUID> {

    Optional<Medicine> findFirstByNameAndForm_Name(String name, FormName formName);

    List<Medicine> findByName(String name);

    Optional<Medicine> findFirstByName(String name);

}
