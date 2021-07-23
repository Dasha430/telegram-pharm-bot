package ua.com.alevel.pharmbot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.pharmbot.model.Medicine;
import ua.com.alevel.pharmbot.model.Search;
import ua.com.alevel.pharmbot.model.User;
import ua.com.alevel.pharmbot.repository.SearchRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SearchHistoryService {
    private final SearchRepository repo;
    private final UserService userService;
    private final FindMedicineService medicineService;

    public SearchHistoryService(SearchRepository repo,
                                UserService userService,
                                FindMedicineService medicineService) {
        this.repo = repo;
        this.userService = userService;
        this.medicineService = medicineService;
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

    @Transactional
    public void save(Long chatId, String medicineName) {

        User u = userService.getById(chatId);
        Medicine m = medicineService.getByName(medicineName);

        if (m == null || u == null) {
            return;
        }
        Search search = new Search();
        search.setUser(u);
        search.setMedicine(m);
        log.info("Saving search ");
        repo.save(search.getPk().getMedicine().getId(), search.getPk().getUser().getChatId());
        log.info("Search saved successfully");
    }
}
