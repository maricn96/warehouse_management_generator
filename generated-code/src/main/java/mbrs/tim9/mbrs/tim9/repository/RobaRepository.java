package mbrs.tim9.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import mbrs.tim9.model.Roba;

@Repository
public interface RobaRepository extends JpaRepository<Roba, Long> {
}
