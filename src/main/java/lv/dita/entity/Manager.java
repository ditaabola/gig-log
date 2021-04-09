package lv.dita.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="managers")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    @ManyToMany (fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "managers_artists", joinColumns = { @JoinColumn(name = "manager_id") }, inverseJoinColumns = { @JoinColumn(name = "artist_id") })
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void addArtists(Artist artist) {
        this.artists.add(artist);
        artist.getManagers().add(this);
    }

    public void removeArtists(Artist artist) {
        this.artists.remove(artist);
        artist.getManagers().remove(this);
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
