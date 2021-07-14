package ua.com.alevel.pharmbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.alevel.pharmbot.model.Search;
import ua.com.alevel.pharmbot.model.SearchId;

import java.util.List;
import java.util.Optional;

public interface SearchRepository extends JpaRepository<Search, SearchId> {

    @Query("select s from Search s where s.pk.user.chatId = :id")
    List<Search> findAllOfUser(Long id);

    @Query("select s from Search s where s.pk.user.chatId = :id and s.searchedAt =" +
            " (select max (se.searchedAt) from Search se where se.pk.user.chatId = :id)")
    Optional<Search> findLatestOfUser(Long id);

}
