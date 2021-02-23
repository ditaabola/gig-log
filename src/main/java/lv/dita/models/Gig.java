package lv.dita.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Gig {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "venueName")
    private String venueName;
    @Column(name = "date")
    private String date;
    @Column(name = "type")
    private String type;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    @JoinTable(name = "gigs_venues", joinColumns = { @JoinColumn(name = "gig_id") }, inverseJoinColumns = { @JoinColumn(name = "venue_id") })
    private Set<Venue> venues = new HashSet<Venue>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    @JoinTable(name = "gigs_artists", joinColumns = { @JoinColumn(name = "gig_id") }, inverseJoinColumns = { @JoinColumn(name = "artist_id") })
    private Set<Artist> artists = new HashSet<Artist>();

    public Gig(String venueName, String date, String type) {
        this.venueName = venueName;
        this.date = date;
        this.type = type;
    }

    public Gig() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVenue() {
        return venueName;
    }

    public void setVenue(String venue) {
        this.venueName = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
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
}
