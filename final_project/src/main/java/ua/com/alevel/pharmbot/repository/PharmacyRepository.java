package ua.com.alevel.pharmbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.com.alevel.pharmbot.model.Pharmacy;

import java.util.Optional;
import java.util.UUID;

public interface PharmacyRepository extends JpaRepository<Pharmacy, UUID> {

    @Query("update Pharmacy p set p.addressGeocode = :geoCoords where p.address=:address")
    @Modifying
    void updateGeoAddress(String address, String geoCoords);

    @Query("select p from Pharmacy p where p.address = :address")
    Optional<Pharmacy> getByAddress(String address);

}
