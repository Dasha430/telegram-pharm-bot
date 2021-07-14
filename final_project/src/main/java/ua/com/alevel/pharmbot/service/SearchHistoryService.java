package ua.com.alevel.pharmbot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.alevel.pharmbot.model.Medicine;
import ua.com.alevel.pharmbot.model.Search;
import ua.com.alevel.pharmbot.model.User;
import ua.com.alevel.pharmbot.repository.SearchRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SearchHistoryService {
    private SearchRepository repo;
    private UserService userService;
    private FindMedicineService medicineService;

    public SearchHistoryService(SearchRepository repo) {
        this.repo = repo;
    }

    public List<String> getHistory(Long id) {
        log.info("Start getting search history of user " + id);
        List<Search> searches = repo.findAllOfUser(id);
        List<String> results = new ArrayList<>();
        for (Search s: searches) {
            results.add(s.getPk().getMedicine().getName());
        }
        log.info("Finish getting search history of user " + id);
        return results;
    }

    public String getLatestMedicine(Long id) {
        log.info("Getting latest search of user " + id);
        Search s = repo.findLatestOfUser(id).orElse(null);

        if (s == null) {
            log.info("Latest search not found");
            return null;
        }
        log.info("Search found successfully");
        return s.getPk().getMedicine().getName();
    }

    public void save(Long chatId, String medicineName) {

        User u = userService.getById(chatId);
        Medicine m = medicineService.getByName(medicineName);
        Search search = new Search();
        search.setUser(u);
        search.setMedicine(m);
        log.info("Saving search ");
        repo.save(search);
        log.info("Search saved successfully");
    }
}
