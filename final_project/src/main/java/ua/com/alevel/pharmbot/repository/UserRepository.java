package ua.com.alevel.pharmbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.pharmbot.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByChatId(Long id);

    Optional<User> findByChatId(Long id);

    @Query("update User u set u.currentAddress = :address, u.currentAddressGeoCode = :geocode where u.chatId = :id")
    @Modifying
    void setAddress(String address, String geocode, Long id);

}
