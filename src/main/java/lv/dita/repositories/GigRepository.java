package lv.dita.repositories;

import lv.dita.models.Artist;
import lv.dita.models.Gig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface GigRepository extends JpaRepository<Gig, Long> {

    @Query("SELECT g FROM Gig g WHERE g.venueName LIKE %?1%" + " OR g.type LIKE %?1%")
    public List<Gig> search(String keyword);
}
