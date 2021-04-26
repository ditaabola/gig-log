package lv.dita.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lv.dita.domain.Manager;

    @Data
    public class ArtistDTO {

        private Long id;
        private String name;
        private String contactEmail;
        private Manager manager;
    }
