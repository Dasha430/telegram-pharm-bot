package ua.com.alevel.pharmbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.pharmbot.model.Search;
import ua.com.alevel.pharmbot.model.SearchId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SearchRepository extends JpaRepository<Search, SearchId> {

    @Query("select s from Search s where s.pk.user.chatId = :id")
    List<Search> findAllOfUser(Long id);

    @Query(value = "select * from searches where user_id = :id and searched_at =" +
            " (select max(searched_at) from searches where user_id =:id)", nativeQuery = true)
    Optional<Search> findLatestOfUser(Long id);

    @Query(value = "insert into searches (medicine_id, user_id) values (:medicine_id, :user_id)", nativeQuery = true)
    @Modifying
    void save(UUID medicine_id, Long user_id);
}
