package mbrs.tim9.service;

import java.util.*;
import mbrs.tim9.model.Roba;
import org.springframework.stereotype.Service;

@Service
public interface RobaService {
    Roba getById(Long id);
	
    Collection<Roba> getAll();
    
    Roba save(Roba roba);
	
    Roba update(Long id, Roba roba);
	
    void delete(Long id);
	
}
