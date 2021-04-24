package lv.dita.repositories;


import lv.dita.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    List<Manager> findByName(String name);
}
