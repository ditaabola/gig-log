package lv.dita.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.dita.enums.GigType;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Gig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private GigType type;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "gigs_venues",
            joinColumns = @JoinColumn(name = "gig_id"),
            inverseJoinColumns = @JoinColumn(name = "venue_id")
    )
    private Set<Venue> gigsInVenues = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "artists_gigs",
            joinColumns = @JoinColumn(name = "gig_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private Set<Artist> artistsWithGigs = new HashSet<>();

    public Gig() {
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