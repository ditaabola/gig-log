package lv.dita.model;

import lombok.Data;
import lv.dita.domain.Artist;
import lv.dita.domain.Venue;
import lv.dita.enums.GigType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
public class GigDTO {

    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private GigType type;
    private Artist artist;
    private Venue venue;
}
