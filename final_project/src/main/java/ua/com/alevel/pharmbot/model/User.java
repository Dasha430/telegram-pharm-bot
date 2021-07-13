package ua.com.alevel.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @NaturalId
    private Long chatId;

    @Column(nullable = false)
    private String username;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.user")
    private List<Search> searchHistory = new ArrayList<>();
}
