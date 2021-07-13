package ua.com.alevel.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class SearchId implements Serializable {

    @ManyToOne
    private Medicine medicine;

    @ManyToOne
    private User user;
}
