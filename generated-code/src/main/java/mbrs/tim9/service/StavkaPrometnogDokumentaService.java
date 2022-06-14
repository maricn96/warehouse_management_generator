package mbrs.tim9.service;

import java.util.*;
import mbrs.tim9.model.StavkaPrometnogDokumenta;
import org.springframework.stereotype.Service;

@Service
public interface StavkaPrometnogDokumentaService {
    StavkaPrometnogDokumenta getById(Long Id);
	
    Collection<StavkaPrometnogDokumenta> getAll();
    
    StavkaPrometnogDokumenta save(StavkaPrometnogDokumenta stavkaPrometnogDokumenta);
	
    StavkaPrometnogDokumenta update(Long id, StavkaPrometnogDokumenta stavkaPrometnogDokumenta);
	
    void delete(Long id);
	
}
