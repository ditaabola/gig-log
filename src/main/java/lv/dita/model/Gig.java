package lv.dita.model;

import lv.dita.enums.GigType;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Gig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private GigType type;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
        @JoinTable(name = "gigs_venues", joinColumns = { @JoinColumn(name = "gig_id") }, inverseJoinColumns = { @JoinColumn(name = "venue_id") })
    private Set<Venue> venues = new HashSet<Venue>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "gigs_artists", joinColumns = { @JoinColumn(name = "gig_id") }, inverseJoinColumns = { @JoinColumn(name = "artist_id") })
    private Set<Artist> artists = new HashSet<Artist>();

    public Gig() {
    }

    public Gig(LocalDate date, GigType type) {
        this.date = date;
        this.type = type;
    }

    public Gig(Long id, LocalDate date, GigType type) {
        this.id = id;
        this.date = date;
        this.type = type;
    }

    public Gig(Long id, LocalDate date, GigType type, Set<Venue> venues, Set<Artist> artists) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.venues = venues;
        this.artists = artists;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public GigType getType() {
        return type;
    }

    public void setType(GigType type) {
        this.type = type;
    }

    public Set<Venue> getVenues() {
        return venues;
    }

    public void setVenues(Set<Venue> venues) {
        this.venues = venues;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public void addVenue(Venue venue) {
        this.venues.add(venue);
        venue.getGigs().add(this);
    }

    public void removeVenues(Venue venue) {
        this.venues.remove(venue);
        venue.getGigs().remove(this);
    }

    public void addArtists(Artist artist) {
        this.artists.add(artist);
        artist.getGigs().add(this);
    }

    public void removeArtists(Artist artist) {
        this.artists.remove(artist);
        artist.getGigs().remove(this);
    }

    @Override
    public String toString() {
        return "Gig{" +
                "date=" + date +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gig gig = (Gig) o;

        return id != null ? id.equals(gig.id) : gig.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
