package ua.com.alevel.pharmbot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.pharmbot.model.User;
import ua.com.alevel.pharmbot.repository.UserRepository;

@Service
@Slf4j
public class UserService {

    private UserRepository repo;
    private MapService mapService;

    public UserService(UserRepository repo, MapService mapService) {
        this.repo = repo;
        this.mapService = mapService;
    }

    public User createUser(Long chatId) {
        log.info("Start creating user " + chatId);
        if (!repo.existsByChatId(chatId)) {
            User u = new User(chatId);
            repo.save(u);
            log.info("User created");
            return u;
        }
        log.info("User already exists");
        return null;
    }

    public User getById(Long chatId) {
        log.info("Getting user by id");
        User u = repo.getById(chatId);
        log.info("User was obtained");
        return  u;
    }

    @Transactional
    public void updateUserAddress(String address, Long chatId) {
        String geocode = mapService.geocodeToString(mapService.toGeoCoordinates(address));

        log.info("Updating user address");
        repo.setAddress(address, geocode, chatId);
        log.info("Finish updating user address");
    }

    @Transactional
    public boolean userHasAddress(Long chatId) {
        User u = repo.getById(chatId);
        return u.getCurrentAddress() != null;
    }

    @Transactional
    public String getUserAddress(Long chatId) {
        User u = repo.getById(chatId);
        return u.getCurrentAddress();
    }

    @Transactional
    public String getUserAddressGeoCode(Long chatId) {
        User u = repo.getById(chatId);
        return u.getCurrentAddressGeoCode();
    }

}
