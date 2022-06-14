package mbrs.tim9.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import mbrs.tim9.repository.RobaRepository;
import org.springframework.stereotype.Service;
import mbrs.tim9.service.RobaService;
import mbrs.tim9.model.Roba;
import java.util.Collection;

@Service
public class RobaServiceImpl implements RobaService {

    @Autowired
    private RobaRepository robaRepository;
    
 	@Override
    public Roba getById(Long id){
        return robaRepository.findById(id).get();
    }
    
    @Override
    public Collection<Roba> getAll(){
        return robaRepository.findAll();
    }
    
 	@Override
    public Roba save(Roba roba){
        return robaRepository.save(roba);
    }
    
 	@Override
    public Roba update(Long id, Roba roba){
    	Roba robaToSave = robaRepository.findById(id).get();
    	
    	robaToSave.setId(roba.getId());
    	robaToSave.setNaziv(roba.getNaziv());
    	robaToSave.setOpis(roba.getOpis());
    	robaToSave.setSifra(roba.getSifra());
    	robaToSave.setCena(roba.getCena());
    	robaToSave.setJedinica_mere(roba.getJedinica_mere());
    	robaToSave.setKategorija(roba.getKategorija());
        return robaRepository.save(robaToSave);
    }
    
 	@Override
    public void delete(Long id){
        robaRepository.deleteById(id);
    }
    
}


