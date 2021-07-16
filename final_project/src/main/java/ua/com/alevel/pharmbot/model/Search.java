package ua.com.alevel.pharmbot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "searches")
@AssociationOverrides({
        @AssociationOverride(name = "pk.medicine",
                joinColumns = @JoinColumn(name = "medicine_id")),
        @AssociationOverride(name = "pk.user",
                joinColumns = @JoinColumn(name = "pharmacy_id")) })

public class Search {

    @EmbeddedId
    private SearchId pk = new SearchId();

    @Column(name = "searched_at")
    private Instant searchedAt;

    public Search() {
        searchedAt = Instant.now();
    }

    public void setMedicine(Medicine medicine) {
        this.pk.setMedicine(medicine);
    }

    public void setUser(User user) {
        this.pk.setUser(user);
    }

}
