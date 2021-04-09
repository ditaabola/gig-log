package lv.dita.entity;

import lv.dita.enums.VenueType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="venues")
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private VenueType type;
    private String country;
    private String city;
    @ManyToMany(mappedBy="venues")
    private Set<Gig> gigs = new HashSet<Gig>();

    public Venue() {
    }

    public Venue(String name, VenueType type, String country, String city) {
        this.name = name;
        this.type = type;
        this.country = country;
        this.city = city;
    }

    public Venue(Long id, String name, VenueType type, String country, String city) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.country = country;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VenueType getType() {
        return type;
    }

    public void setType(VenueType type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Gig> getGigs() {
        return gigs;
    }

    public void setGigs(Set<Gig> gigs) {
        this.gigs = gigs;
    }

    public void addGig(Gig gig) {
        this.gigs.add(gig);
        gig.getVenues().add(this);
    }

    public void removeGigs(Gig gig) {
        this.gigs.remove(gig);
        gig.getVenues().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Venue venue = (Venue) o;

        return id != null ? id.equals(venue.id) : venue.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
