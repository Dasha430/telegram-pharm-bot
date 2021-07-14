package ua.com.alevel.pharmbot.model;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.user")
    private List<Search> searchHistory = new ArrayList<>();

    @Column(name = "current_address")
    private String currentAddress;

    @Column(name = "current_address_geocode")
    private String currentAddressGeoCode;

    public User(){

    }

    public User(Long chatId){
        this.chatId = chatId;
    }
}
