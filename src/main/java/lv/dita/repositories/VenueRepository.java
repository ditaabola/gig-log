package lv.dita.repositories;

import lv.dita.entity.Venue;
import org.springframework.data.repository.CrudRepository;

public interface VenueRepository extends CrudRepository<Venue, Long> {
}
