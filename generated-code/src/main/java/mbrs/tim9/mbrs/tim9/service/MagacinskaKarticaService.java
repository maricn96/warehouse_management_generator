package mbrs.tim9.service;

import java.util.*;
import mbrs.tim9.model.MagacinskaKartica;
import org.springframework.stereotype.Service;

@Service
public interface MagacinskaKarticaService {
    MagacinskaKartica getById(Long id);
	
    Collection<MagacinskaKartica> getAll();
    
    MagacinskaKartica save(MagacinskaKartica magacinskaKartica);
	
    MagacinskaKartica update(Long id, MagacinskaKartica magacinskaKartica);
	
    void delete(Long id);
	
}
