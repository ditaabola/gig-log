package lv.dita.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class ManagerDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;

    private Long artist;
}
