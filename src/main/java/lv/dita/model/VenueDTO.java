package lv.dita.model;

import lv.dita.enums.VenueType;

import javax.validation.constraints.Size;

public class VenueDTO {

    private VenueType type;
    private String country;
    private String city;
}
