package lv.dita.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;

    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    private List<Artist> artistList;

//    public Manager() {
//    }
//
//    public Manager(String name, String surname, String email) {
//        this.name = name;
//        this.surname = surname;
//        this.email = email;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Manager manager = (Manager) o;
//
//        return id != null ? id.equals(manager.id) : manager.id == null;
//    }
//
//    @Override
//    public int hashCode() {
//        return id != null ? id.hashCode() : 0;
//    }
//
//    @Override
//    public String toString() {
//        return "Manager{" +
//                "name='" + name + '\'' +
//                ", surname='" + surname + '\'' +
//                ", email='" + email + '\'' +
//                '}';
//    }
}




