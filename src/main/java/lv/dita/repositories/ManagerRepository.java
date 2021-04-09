package lv.dita.repositories;

import lv.dita.entity.Manager;
import org.springframework.data.repository.CrudRepository;

public interface ManagerRepository extends CrudRepository<Manager, Long> {
}
