package lv.dita.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @ManyToMany
    private Set<Gig> gigs = new HashSet<Gig>();

    public Venue() {
    }

    public Venue(String name, String type, String country, String city) {

        this.country = country;
        this.city = city;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
}
