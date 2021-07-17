package ua.com.alevel.pharmbot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.pharmbot.model.MedInPharmacy;
import ua.com.alevel.pharmbot.model.Medicine;
import ua.com.alevel.pharmbot.model.Pharmacy;
import ua.com.alevel.pharmbot.model.records.MedInPharmacyRecord;
import ua.com.alevel.pharmbot.repository.MedicineRepository;
import ua.com.alevel.pharmbot.repository.MedsInPharmacyRepository;
import ua.com.alevel.pharmbot.repository.PharmacyRepository;
import ua.com.alevel.pharmbot.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FindMedicineService {

    private MedsInPharmacyRepository repo;
    private MapService mapService;
    private MedicineRepository medsRepo;
    private PharmacyRepository pharmRepo;

    public FindMedicineService(MedsInPharmacyRepository repo,
                               MapService mapService,
                               MedicineRepository medsRepo,
                               PharmacyRepository pharmRepo) {
        this.repo = repo;
        this.mapService = mapService;
        this.medsRepo = medsRepo;
        this.pharmRepo = pharmRepo;
    }

    @Transactional
    public List<MedInPharmacyRecord> getByNameAndAddress(String name, String address){
        log.info("Getting medicine by name");
        List<MedInPharmacy> results = repo.findByMedicineNameAndPharmacyAddress(name, address);

        if (results == null || results.isEmpty()) {
            log.info("Pharmacy with such medicine and address not found");
            return null;
        }
        List<MedInPharmacyRecord> records = new ArrayList<>();
        for (MedInPharmacy r: results) {
            records.add(new MedInPharmacyRecord(r.getMedicine(), r.getPharmacy(), r.getPrice()));
        }
        return records;
    }

    public Medicine getByName(String name) {
        log.info("Getting medicine by name");
        Medicine m = medsRepo.findFirstByName(name).orElse(null);
        if (m == null) {
            log.info("Medicine not found");
            return null;
        }
        log.info("Medicine obtained");
        return m;
    }

    @Transactional
    public List<String> getNearestAddressesWhereMedicinePresent(String name, String userAddress) {
        log.info("Start finding pharmacies");
        List<MedInPharmacy> allPharmacies = repo.findAllAddressesWherePresent(name);
        log.info("Finish finding pharmacies");

        if (allPharmacies == null || allPharmacies.isEmpty()) {
            log.info("Pharmacies not found");
            return null;
        }

        Set<String> addresses = allPharmacies
                .stream()
                .map(MedInPharmacy::getPharmacy)
                .map(Pharmacy::getAddress)
                .collect(Collectors.toSet());


        return findNearest(userAddress, addresses);
    }


    private String convertToGeocode(String address) {
        Map<String, Double> result =  mapService.toGeoCoordinates(address);
        String coords = "lng:" + result.get("lng") + " lat:" + result.get("lat");
        Pharmacy p = pharmRepo.getByAddress(address).orElse(null);
        if (p != null){
            if ( p.getAddressGeocode() == null) {
                pharmRepo.updateGeoAddress(address, coords);
            }
        }
        return coords;
    }

    private List<String> findNearest(String from, Set<String>tos) {
        List<String> destinations = new ArrayList<>();

        for (String to: tos) {
            String converted = convertToGeocode(to);
            destinations.add(converted);
        }
        return mapService.calculateNearestDistances(from, destinations);
    }

}
