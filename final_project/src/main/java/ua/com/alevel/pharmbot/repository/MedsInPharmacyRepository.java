package ua.com.alevel.pharmbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.alevel.pharmbot.model.MedInPharmacy;
import ua.com.alevel.pharmbot.model.MedInPharmacyId;

import java.util.List;


public interface MedsInPharmacyRepository extends JpaRepository<MedInPharmacy, MedInPharmacyId> {

    @Query("select mp from MedInPharmacy mp " +
            "where mp.pk.medicine.name = :name order by mp.pk.pharmacy.address")
    List<MedInPharmacy> findAllAddressesWherePresent(String name);

    @Query("select mp from MedInPharmacy mp " +
            "where mp.pk.medicine.name = :name and mp.pk.pharmacy.address = :address and mp.number > 0")
    List<MedInPharmacy> findByMedicineNameAndPharmacyAddress(String name, String address);
}
