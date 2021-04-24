package lv.dita.domain;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contactEmail;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;

//    @ManyToMany(mappedBy = "artistsWithGigs")
//    private Set<Gig> gigs = new HashSet<>();

//    public Artist() {
//
//    }
//
//    public Artist(Long id, String name, String contactEmail) {
//        this.id = id;
//        this.name = name;
//        this.contactEmail = contactEmail;
//    }
//
//    @Override
//    public String toString() {
//        return "Artist{" +
//                "name='" + name + '\'' +
//                ", contactEmail='" + contactEmail + '\'' +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Artist artist = (Artist) o;
//
//        return id != null ? id.equals(artist.id) : artist.id == null;
//    }
//
//    @Override
//    public int hashCode() {
//        return id != null ? id.hashCode() : 0;
//    }
}