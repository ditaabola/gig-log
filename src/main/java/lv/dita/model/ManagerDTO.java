package lv.dita.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ManagerDTO {

    private Long id;

    private String name;

    private String surname;

    private String email;

}
