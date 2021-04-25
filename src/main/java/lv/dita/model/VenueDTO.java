package lv.dita.model;

import lombok.Getter;
import lombok.Setter;
import lv.dita.enums.VenueType;

@Getter
@Setter
public class VenueDTO {

    private Long id;
    private VenueType type;
    private String name;
    private String country;
    private String city;
}

