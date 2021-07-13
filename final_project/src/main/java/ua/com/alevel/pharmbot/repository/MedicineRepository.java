package ua.com.alevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.alevel.model.Medicine;


import java.util.Optional;
import java.util.UUID;

public interface MedicineRepository extends JpaRepository<Medicine, UUID> {

    Optional<Medicine> findById(UUID id);

    @Query("select m.instruction from Medicine m where m.id = :id")
    String findInstructionById(UUID id);



}
