package lv.dita.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contactEmail;

    @ManyToMany (fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "artists_managers", joinColumns = { @JoinColumn(name = "artist_id") }, inverseJoinColumns = { @JoinColumn(name = "manager_id") })
    private Set<Manager> managers = new HashSet<>();

    @ManyToMany(mappedBy = "artists")
    private Set<Gig> gigs = new HashSet<>();

    public Artist() {
    }

    public Artist(String name, String contactEmail) {
        this.name = name;
        this.contactEmail = contactEmail;
    }

    public Artist(Long id, String name, String contactEmail) {
        this.id = id;
        this.name = name;
        this.contactEmail = contactEmail;
    }

    public Artist(Long id, String name, String contactEmail, Set<Manager> managers) {
        this.id = id;
        this.name = name;
        this.contactEmail = contactEmail;
        this.managers = managers;
    }

    @Override
    public String toString() {
        return "Artist{" +
                name + '\'' +
                ", contact e-mail: " + contactEmail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        return id != null ? id.equals(artist.id) : artist.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
