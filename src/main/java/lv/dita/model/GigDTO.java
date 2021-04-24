package lv.dita.model;

import lombok.Getter;
import lombok.Setter;
import lv.dita.domain.Artist;
import lv.dita.domain.Venue;
import lv.dita.enums.GigType;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class GigDTO {

    private Long id;

    private LocalDate date;

    private GigType type;

    private List<Long> artistsWithGigs;

    private List<Long> gigsInVenues;

}
