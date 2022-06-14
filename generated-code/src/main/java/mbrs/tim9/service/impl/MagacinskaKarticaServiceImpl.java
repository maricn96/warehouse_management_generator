package mbrs.tim9.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import mbrs.tim9.repository.MagacinskaKarticaRepository;
import org.springframework.stereotype.Service;
import mbrs.tim9.service.MagacinskaKarticaService;
import mbrs.tim9.model.MagacinskaKartica;
import java.util.Collection;

@Service
public class MagacinskaKarticaServiceImpl implements MagacinskaKarticaService {

    @Autowired
    private MagacinskaKarticaRepository magacinskaKarticaRepository;
    
 	@Override
    public MagacinskaKartica getById(Long id){
        return magacinskaKarticaRepository.findById(id).get();
    }
    
    @Override
    public Collection<MagacinskaKartica> getAll(){
        return magacinskaKarticaRepository.findAll();
    }
    
 	@Override
    public MagacinskaKartica save(MagacinskaKartica magacinskaKartica){
        return magacinskaKarticaRepository.save(magacinskaKartica);
    }
    
 	@Override
    public MagacinskaKartica update(Long id, MagacinskaKartica magacinskaKartica){
    	MagacinskaKartica magacinskaKarticaToSave = magacinskaKarticaRepository.findById(id).get();
    	
    	magacinskaKarticaToSave.setId(magacinskaKartica.getId());
    	magacinskaKarticaToSave.setPromet_ulaza_kolicina(magacinskaKartica.getPromet_ulaza_kolicina());
    	magacinskaKarticaToSave.setPromet_izlaza_kolicina(magacinskaKartica.getPromet_izlaza_kolicina());
    	magacinskaKarticaToSave.setUkupna_kolicina(magacinskaKartica.getUkupna_kolicina());
    	magacinskaKarticaToSave.setPromet_ulaza_vrednost(magacinskaKartica.getPromet_ulaza_vrednost());
    	magacinskaKarticaToSave.setPromet_izlaza_vrednost(magacinskaKartica.getPromet_izlaza_vrednost());
    	magacinskaKarticaToSave.setUkupna_vrednost(magacinskaKartica.getUkupna_vrednost());
        return magacinskaKarticaRepository.save(magacinskaKarticaToSave);
    }
    
 	@Override
    public void delete(Long id){
        magacinskaKarticaRepository.deleteById(id);
    }
    
}


