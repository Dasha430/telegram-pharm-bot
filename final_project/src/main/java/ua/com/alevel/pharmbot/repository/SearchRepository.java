package ua.com.alevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.alevel.model.Search;
import ua.com.alevel.model.SearchId;

import java.util.List;

public interface SearchRepository extends JpaRepository<Search, SearchId> {

    List<Search> findAll();
}
