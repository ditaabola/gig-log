package lv.dita.enums;

public enum GigType {
    PRIVATE_GIG ("Private gig"),
    CORPORATE_GIG ("Corporate gig"),
    RECORDING ("Recording"),
    LIVE_CONCERT ("Live concert");


    private final String displayValue;

    GigType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;

    }

}
