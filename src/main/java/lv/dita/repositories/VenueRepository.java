package lv.dita.repositories;

import lv.dita.models.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Long> {
}
