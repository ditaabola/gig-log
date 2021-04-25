package lv.dita.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;

    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    private List<Artist> artistList = new ArrayList<>();

}




