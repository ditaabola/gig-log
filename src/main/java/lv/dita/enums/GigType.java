package lv.dita.enums;

import org.hibernate.engine.spi.Status;

import java.util.HashMap;
import java.util.Map;

public enum GigType {
    PRIVATE_GIG ("Private gig"),
    CORPORATE_GIG ("Corporate gig"),
    RECORDING ("Recording"),
    LIVE_CONCERT ("Live concert");


    private final String displayValue;

    private GigType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;

    }

}
