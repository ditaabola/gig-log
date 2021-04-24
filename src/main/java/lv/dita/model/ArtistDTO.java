package lv.dita.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.dita.domain.Manager;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ArtistDTO {

        private Long id;
        private String name;
        private String contactEmail;
        private List<Manager> managers;
}
