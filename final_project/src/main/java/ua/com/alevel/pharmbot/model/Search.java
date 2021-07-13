package ua.com.alevel.model;

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

    private Instant searchedAt;

    public Search() {
        searchedAt = Instant.now();
    }

}
