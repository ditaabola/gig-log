package lv.dita.repositories;

import lv.dita.model.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Long> {
}
