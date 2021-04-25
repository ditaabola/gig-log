package lv.dita.domain;

import lombok.Data;
import lv.dita.enums.VenueType;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private VenueType type;
    private String country;
    private String city;

    @OneToMany(mappedBy = "venue", fetch = FetchType.LAZY)
    private List<Gig> gigs = new ArrayList<>();


}
