package mbrs.tim9.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import mbrs.tim9.model.MagacinskaKartica;

@Repository
public interface MagacinskaKarticaRepository extends JpaRepository<MagacinskaKartica, Long> {
}
