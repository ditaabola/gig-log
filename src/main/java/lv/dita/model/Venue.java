package lv.dita.model;

import lombok.Getter;
import lombok.Setter;
import lv.dita.enums.VenueType;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private VenueType type;
    private String country;
    private String city;
    @ManyToMany(mappedBy="venues")
    private Set<Gig> gigs = new HashSet<>();

    public Venue() {
    }

    public Venue(String name, VenueType type, String country, String city) {
        this.name = name;
        this.type = type;
        this.country = country;
        this.city = city;
    }

    public Venue(Long id, String name, VenueType type, String country, String city) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.country = country;
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Venue venue = (Venue) o;

        return id != null ? id.equals(venue.id) : venue.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
