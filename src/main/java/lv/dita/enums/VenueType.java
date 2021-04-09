package lv.dita.enums;

public enum VenueType {
    ROCK_CLUB ("Rock club"),
    CONCERT_HAL ("Concert hall"),
    ALTERNATIVE_CLUB ("Alternative club"),
    NIGHTCLUB ("Night club"),
    DISCO_HALL ("Disco hall"),
    OPERA_HOUSE ("Opera house"),
    JAZZ_CLUB ("Jazz club"),
    PRIVATE_VENUE ("Private venue"),
    CORPORATE_VENUE ("Corporate venue"),
    STADIUM ("Stadium"),
    ARENA ("Arena");


    private final String displayValue;

    private VenueType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;

    }
}
