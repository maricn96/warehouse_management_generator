package mbrs.tim9.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import mbrs.tim9.repository.StavkaPrometnogDokumentaRepository;
import org.springframework.stereotype.Service;
import mbrs.tim9.service.StavkaPrometnogDokumentaService;
import mbrs.tim9.model.StavkaPrometnogDokumenta;
import java.util.Collection;

@Service
@Qualifier("stavkaPrometnogDokumentaServiceImpl")
public class StavkaPrometnogDokumentaServiceImpl implements StavkaPrometnogDokumentaService {

    @Autowired
    private StavkaPrometnogDokumentaRepository stavkaPrometnogDokumentaRepository;
    
 	@Override
    public StavkaPrometnogDokumenta getById(Long id){
        return stavkaPrometnogDokumentaRepository.findById(id).get();
    }
    
    @Override
    public Collection<StavkaPrometnogDokumenta> getAll(){
        return stavkaPrometnogDokumentaRepository.findAll();
    }
    
 	@Override
    public StavkaPrometnogDokumenta save(StavkaPrometnogDokumenta stavkaPrometnogDokumenta){
        return stavkaPrometnogDokumentaRepository.save(stavkaPrometnogDokumenta);
    }
    
 	@Override
    public StavkaPrometnogDokumenta update(Long id, StavkaPrometnogDokumenta stavkaPrometnogDokumenta){
    	StavkaPrometnogDokumenta stavkaPrometnogDokumentaToSave = stavkaPrometnogDokumentaRepository.findById(id).get();
    	
    	stavkaPrometnogDokumentaToSave.setId(stavkaPrometnogDokumenta.getId());
    	stavkaPrometnogDokumentaToSave.setKolicina(stavkaPrometnogDokumenta.getKolicina());
    	stavkaPrometnogDokumentaToSave.setUkupna_cena(stavkaPrometnogDokumenta.getUkupna_cena());
    	stavkaPrometnogDokumentaToSave.setDatum_i_vreme(stavkaPrometnogDokumenta.getDatum_i_vreme());
    	stavkaPrometnogDokumentaToSave.setVrsta_prometa(stavkaPrometnogDokumenta.getVrsta_prometa());
        return stavkaPrometnogDokumentaRepository.save(stavkaPrometnogDokumentaToSave);
    }
    
 	@Override
    public void delete(Long id){
        stavkaPrometnogDokumentaRepository.deleteById(id);
    }
    
}


