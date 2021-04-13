package lv.dita.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;

    @ManyToMany(mappedBy = "managers")
    private Set<Artist> artists = new HashSet<>();

    public Manager(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Manager(String name, String surname, String email, Set<Artist> artists) {
        this.artists = artists;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Manager(Long id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Manager() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Manager manager = (Manager) o;

        return id != null ? id.equals(manager.id) : manager.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
