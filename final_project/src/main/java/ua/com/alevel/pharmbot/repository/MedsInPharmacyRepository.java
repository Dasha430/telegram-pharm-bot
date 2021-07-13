package ua.com.alevel.pharmbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.alevel.pharmbot.model.Pharmacy;

import java.util.List;
import java.util.UUID;

public interface PharmacyRepository extends JpaRepository<Pharmacy, UUID> {

    @Query("select p.address from Pharmacy p order by p.address")
    List<Pharmacy> findAllAddresses();
}
