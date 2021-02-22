package lv.dita.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;
    @Column(name = "contactEmail", length = 100, nullable = false)
    private String contactEmail;
    @ManyToMany
    private Set<Gig> gigs = new HashSet<Gig>();

    public Artist() {
    }

    public Artist(String name, String contactEmail) {
        this.name = name;
        this.contactEmail = contactEmail;
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

    public boolean hasGigs(Optional<Gig> gig) {
        for (Gig containedGig : getGigs()) {
            if (containedGig.getId() == gig.get().getId()) {
                return true;
            }
        }
        return false;
    }

    public void addGigs(Gig gig) {
        this.gigs.add(gig);
        gig.getArtists().add(this);
    }

    public void removeGigs(Gig gig) {
        this.gigs.remove(gig);
        gig.getArtists().remove(this);
    }

    @Override
    public String toString() {
        return "Artist{" +
                name + '\'' +
                ", contact e-mail: " + contactEmail + '\'' +
                '}';
    }
}
