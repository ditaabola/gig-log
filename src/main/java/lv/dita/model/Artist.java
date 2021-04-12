package lv.dita.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "contact_email")
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

    public Set<Manager> getManagers() {
        return managers;
    }

    public void setManagers(Set<Manager> managers) {
        this.managers = managers;
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

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Set<Gig> getGigs() {
        return gigs;
    }

    public void setGigs(Set<Gig> gigs) {
        this.gigs = gigs;
    }

//    public boolean hasGigs(Optional<Gig> gig) {
//        for (Gig containedGig : getGigs()) {
//            if (containedGig.getId() == gig.get().getId()) {
//                return true;
//            }
//        }
//        return false;
//    }

    public void addGigs(Gig gig) {
        this.gigs.add(gig);
        gig.getArtists().add(this);
    }

    public void removeGigs(Gig gig) {
        this.gigs.remove(gig);
        gig.getArtists().remove(this);
    }

    public void addManagers(Manager manager) {
        this.managers.add(manager);
        manager.getArtists().add(this);
    }

    public void removeManagers(Manager manager) {
        this.managers.remove(manager);
        manager.getArtists().remove(this);
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
