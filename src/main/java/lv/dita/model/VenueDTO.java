package lv.dita.model;

import lombok.Data;
import lv.dita.enums.VenueType;

@Data
public class VenueDTO {

    private Long id;
    private VenueType type;
    private String name;
    private String country;
    private String city;
}

