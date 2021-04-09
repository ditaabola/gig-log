package lv.dita.repositories;

import lv.dita.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {
}
