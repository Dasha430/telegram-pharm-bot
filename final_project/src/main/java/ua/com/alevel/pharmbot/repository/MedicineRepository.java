package ua.com.alevel.pharmbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import ua.com.alevel.pharmbot.model.Form;
import ua.com.alevel.pharmbot.model.FormName;
import ua.com.alevel.pharmbot.model.Medicine;


import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface MedicineRepository extends JpaRepository<Medicine, UUID> {

    Optional<Medicine> findFirstByNameAndForm_Name(String name, FormName formName);

    List<Medicine> findByName(String name);

    Optional<Medicine> findFirstByName(String name);

    @Query( value = "select f.name from forms f where f.id = (select form_id from medicines where id = :id)", nativeQuery = true)
    String findFormById(UUID id);

}
