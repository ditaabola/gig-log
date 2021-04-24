package lv.dita.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.dita.enums.VenueType;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private VenueType type;
    private String country;
    private String city;
    @ManyToMany(mappedBy="gigsInVenues")
    private Set<Gig> gigs = new HashSet<>();

//    public Venue() {
//    }
//
//    public Venue(String name, VenueType type, String country, String city) {
//        this.name = name;
//        this.type = type;
//        this.country = country;
//        this.city = city;
//    }
//
//    public Venue(Long id, String name, VenueType type, String country, String city) {
//        this.id = id;
//        this.name = name;
//        this.type = type;
//        this.country = country;
//        this.city = city;
//    }


}
